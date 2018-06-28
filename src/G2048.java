import java.util.Scanner;

public class G2048 {
    private Pane p = new Pane();
    private static boolean GAMEING = true;


    public void start() {
        Direction dir = null;
        Scanner scanner = new Scanner(System.in);
        initGame();
        do {
            String op = scanner.next();
            switch (op) {
                case "w":
                    dir = Direction.UP;
                    break;
                case "a":
                    dir = Direction.LEFT;
                    break;
                case "s":
                    dir = Direction.DOWN;
                    break;
                case "d":
                    dir = Direction.RIGHT;
                    break;
                case "over":
                    dir = Direction.OVER;
                    break;
                default : dir = Direction.ERR;
                    break;
            }
            executeWithOperation(dir);
            nextStep(dir);
        } while (GAMEING);
    }

    private void nextStep(Direction dir) {
        if (dir != Direction.ERR) {
            p.show();
            p.setARandNumberInPane();
        }
    }

    private void initGame() {
        p.setARandNumberInPane();
        p.setARandNumberInPane();
        p.show();
    }

    public void showGameRule() {
        System.out.println("欢迎来到2048");
        System.out.println("用wasd操作方向over退出");
    }

    private void executeWithOperation(Direction dir) {
        switch (dir) {
            case UP:
                p._rotate90();
                p.collisionAlgorithm();
                p.rotate90();
                break;
            case LEFT:
                p.collisionAlgorithm();
                break;
            case DOWN:
                p.rotate90();
                p.collisionAlgorithm();
                p._rotate90();
                break;
            case RIGHT:
                p._rotate180();
                p.collisionAlgorithm();
                p._rotate180();
                break;
            case OVER:
                GAMEING = false;
                break;
            case ERR:
                System.out.println("你输入了错误的操作码,请重新输入");
        }
    }
}
