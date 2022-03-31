package qq.com.zl.view;


import qq.com.zl.comment.User;
import qq.com.zl.service.UserClientSercive;
import qq.com.zl.utils.ScanerUtils;

/**
 * @author zhaolun
 * @version 1.0
 * @item xxx
 */
public class View {
    private static String username = "";
    private static UserClientSercive userClientSercive = new UserClientSercive();

    public static void firstView() {
        System.out.println("====================欢迎登录网络通信系统===========");
        System.out.println("                     1.登录系统                  ");
        System.out.println("                     2.注册用户                  ");
        System.out.println("                     9.退出系统                  ");
        System.out.println("************************************************");
    }

    /**
     * 二级菜单
     */
    public static void twoView() {
        boolean loop = true;
        while (loop) {
            System.out.println("====================网络通信二级菜单===============");
            System.out.println("                     1.显示在线用户                 ");
            System.out.println("                     2.群发消息                  ");
            System.out.println("                     3.私聊消息                  ");
            System.out.println("                     4.发送文件                  ");
            System.out.println("                     9.退出系统                  ");
            System.out.println("************************************************");
            char c = ScanerUtils.inputChar("请输入你的选择：");
            switch (c) {
                case '1':

                    userClientSercive.getOnline();

                    break;
                case '2':
                    String string = ScanerUtils.inputString("你要对大家说：");
                    userClientSercive.chatAll(username, string);

                    break;
                case '3':
                    userClientSercive.getOnline();
                    String receiver = ScanerUtils.inputString("请选择你要发送的对象");
                    String content = ScanerUtils.inputString("你要对" + receiver + "说：");
                    userClientSercive.chatUser(username, receiver, content);
                    break;
                case '4':
                    break;
                case '9':
                    loop = ScanerUtils.exit(loop);
                    break;


            }

        }
    }


    /**
     * 登录功能
     */
    public static void login() {

        username = ScanerUtils.inputString("请输入你的用户id:");
        String password = ScanerUtils.inputString("请输入你的密码:");

        if (userClientSercive.checkLogin(username, password)) {
            System.out.println("================欢迎您" + username + "！！===============");
            twoView();


        } else {
            System.out.println("========用户名或密码错误===========");
        }

    }

    /**
     * 登录
     *
     * @return 合法用户
     */
    public static User resign() {

        String name = ScanerUtils.inputString("请输入长度在3-9的用户名:");
        String password = ScanerUtils.inputString("请输入密码：");
        String password1 = ScanerUtils.inputString("请确认密码：");
        if (!(name != null && name.length() > 1 && name.length() < 9)) {
            System.out.println("*****用户名格式不对******");

            return null;
        }
        if (!(password != null && password.equals(password1))) {
            System.out.println("******两次输入的密码不一致******");

            return null;
        }

        return new User(name, password, 1);

    }

    public void showLineUser() {

    }

    public static void menu() {
        boolean loop = true;
        while (loop) {
            firstView();
            char c = ScanerUtils.inputChar("请输入你的选择：");
            switch (c) {
                case '1':
                    login();
                    break;
                case '2':
                    if (userClientSercive.checkResign()) {
                        System.out.println("********注册成功**********");
                    } else {
                        System.out.println("-------注册失败----------");
                    }

                    break;
                case '3':

                    break;
                case '9':

                    loop = ScanerUtils.exit(loop);
                    break;
                default:
                    System.out.println("你的输入有误，请重新输入");
                    break;

            }

        }
    }

}
