package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import exceptions.TimeOutException;

public class UDPReadWrite {
    private DatagramChannel channel;
    private int bufferSize;
    private ByteBuffer buffer;

    public UDPReadWrite(DatagramChannel channel, int bufferSize) {
        this.channel = channel;
        this.bufferSize = bufferSize;
        this.buffer = ByteBuffer.allocate(this.bufferSize);
    }

    public UDPMessage read(int timeOut) throws IOException, InterruptedException, TimeOutException {
        long time = System.currentTimeMillis();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        while (true) {
            InetSocketAddress clientAddress = (InetSocketAddress)channel.receive(buffer);

            if(clientAddress == null) {
                if (timeOut > 0 && System.currentTimeMillis() - time > timeOut) throw new TimeOutException();
                Thread.sleep(10);
                continue;
            }

            buffer.flip();
            int bytesRead = buffer.remaining();
            byte[] bytesArray = new byte[bytesRead];
            buffer.get(bytesArray, 0, bytesRead);
            stream.write(bytesArray);
            buffer.clear();

            if(stream.size() < 4) continue;
            byte[] lengthBuffer = stream.toByteArray();
            int length = ((lengthBuffer[0] & 0xFF) << 24) | ((lengthBuffer[1] & 0xFF) << 16) | ((lengthBuffer[2] & 0xFF) << 8) | (lengthBuffer[3] & 0xFF);

            if(stream.size() - 4 < length) continue;
            byte[] dataWithPrefix = stream.toByteArray();
            
            byte[] data = new byte[length];
            System.arraycopy(dataWithPrefix, 4, data, 0, length);

            String body = new String(data, StandardCharsets.UTF_8);
            return new UDPMessage(clientAddress, body);
        }
    }

    public void write(UDPMessage response) throws IOException {
        byte[] bytesMessage = response.body.getBytes(StandardCharsets.UTF_8);
        byte[] bytesLength = new byte[] { (byte)(bytesMessage.length >> 24), (byte)(bytesMessage.length >> 16),
            (byte)(bytesMessage.length >> 8), (byte)(bytesMessage.length) };
        byte[] bytes = new byte[bytesLength.length + bytesMessage.length];
        System.arraycopy(bytesLength, 0, bytes, 0, bytesLength.length);
        System.arraycopy(bytesMessage, 0, bytes, bytesLength.length, bytesMessage.length);

        for (int i = 0; i < bytes.length; i += bufferSize) {
            int endIndex = Math.min(i + bufferSize, bytes.length);
            byte[] chunk = new byte[endIndex - i];
            System.arraycopy(bytes, i, chunk, 0, endIndex - i);

            ByteBuffer buffer = ByteBuffer.wrap(chunk);
            channel.send(buffer, response.addr);
        }
    }
}