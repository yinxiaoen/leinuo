import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/4/16.
 */
public class BigDecimalTest {
    private static final int DEF_DIV_SCALE = 8;
    public static void main(String [] args){
        BigDecimal b= new BigDecimal(-0.000058961);
        b = b.setScale(DEF_DIV_SCALE, BigDecimal.ROUND_UP);
        System.out.println(b);
    }

}
