import java.time.ZonedDateTime;

/**
 * Author:lzj
 * Create:2022-04-16-14:29
 * Describe:
 */
public class T2 {
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);
        //2022-04-16T14:29:44.619+08:00[Asia/Shanghai]
    }
}
