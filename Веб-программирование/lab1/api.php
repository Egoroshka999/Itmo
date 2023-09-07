<?php
$time = microtime(true);
function isInside($x, $y, $r)
{
    if ($x <= $r && $y <= $r) {
        if ($x >= 0 && $y >= 0 && $x ** 2 + $y ** 2 <= ($r/2) ** 2) {
            return true;
        } elseif ($x < 0 && $y > 0) {
            return true;
            
        } elseif ($x > 0 && $y < 0) {

            $a = (0 - $x) * (0 - 0) - ($r - 0) * (0 - $y);
            $b = ($r - $x) * (-$r - 0) - (0 - $r) * (0 - $y);
            $c = (0 - $x) * (0 + $r) - (0 - 0) * (-$r - $y);
            if (($a >= 0 && $b >= 0 && $c >= 0) || ($a <= 0 && $b <= 0 && $c <= 0)) {
                return true;
            }
        } else {
            return false;
        }
    } else {
        return false;
    }
}

if (isset($_POST['R']) && isset($_POST['X']) && isset($_POST['Y'])) {
    $r = intval($_POST['R']);
    $x = intval($_POST['X']);
    $y = intval($_POST['Y']);

    $isInside = isInside($x, $y, $r);

    echo json_encode(["Inside"=>$isInside,"X"=>$x,"Y"=>$y,"R"=>$r, "exec"=> microtime(true)-$time, "time" => time(),"error"=>null]);
} else {
    echo json_encode(["Inside"=>false,"X"=>$x,"Y"=>$y,"R"=>$r, "exec"=> microtime(true)-$time, "time" => time(), "error"=>"Invalid input data. Please go back and enter valid values."]);
}
?>
