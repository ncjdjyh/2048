import java.util.Scanner;

public class G2048 {
    private Pane p;
    private static boolean GAMEING = true;
    Scanner scanner;
    UserOperation userOperation;

    public G2048() {
        p  = new Pane();
        scanner = new Scanner(System.in);
    }


    public void start() {
        initGame();
        do {
            String op = scanner.next();
            switch (op) {
                case "w":
                    userOperation = UserOperation.UP;
                    break;
                case "a":
                    userOperation = UserOperation.LEFT;
                    break;
                case "s":
                    userOperation = UserOperation.DOWN;
                    break;
                case "d":
                    userOperation = UserOperation.RIGHT;
                    break;
                case "over":
                    userOperation = UserOperation.OVER;
                    break;
                default : userOperation = UserOperation.ERR;
                    break;
            }
            executeWithOperation(userOperation);
            nextStep(userOperation);
        } while (GAMEING);
    }

    private void nextStep(UserOperation dir) {
        if (dir != UserOperation.ERR) {
            p.setARandNumberInPane();
            if (!isGameOver()) {
                p.show();
            } else {
                gameFailOperation();
            }
        }
    }

    private void gameFailOperation() {
        System.out.println("你输了,输入restart重新开始,输入over结束游戏");
        do {
            String op = scanner.next();
            if (op.equals("over")) {
                userOperation = UserOperation.OVER;
                break;
            } else if(op.equals("restart")) {
                userOperation = UserOperation.RESTART;
                break;
            } else {
                userOperation = UserOperation.ERR;
            }
            executeWithOperation(userOperation);
        } while (true);
        executeWithOperation(userOperation);
    }

    private void initGame() {
        p.setARandNumberInPane();
        p.setARandNumberInPane();
        p.show();
    }

    private boolean isGameOver() {
        return p.isFull();
    }

    public void showGameRule() {
        System.out.println("欢迎来到2048");
        System.out.println("用wasd操作方向over退出");
    }

    private void executeWithOperation(UserOperation op) {
        switch (op) {
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
                stopGame();
                break;
            case ERR:
                System.out.println("你输入了错误的操作码,请重新输入");
                break;
            case RESTART:
                p.reload();
                initGame();
                break;
        }
    }

    private void stopGame() {
        GAMEING = false;
        System.out.println("游戏结束");
    }
}
