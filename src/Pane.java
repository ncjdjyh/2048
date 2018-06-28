import java.util.Random;

public class Pane {
    private static final int SIZE = 5;
    private int[][] p;
    private Random random;

    public Pane() {
        p = new int[SIZE][SIZE];
        random = new Random();
    }

    public void setRandPane() {
        int rand = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                rand = (int) (Math.random() * 4);
                p[i][j] = rand;
            }
        }
    }

    //顺时针旋转90
    public void rotate90() {
        int[][] tempPane = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tempPane[j][SIZE - i - 1] = p[i][j];
            }
        }
        p = tempPane;
    }

    //逆时针旋转90
    public void _rotate90() {
        rotate90();
        _rotate180();
        rotate180();
    }

    //上下翻转
    public void rotate180() {
        for (int i = 0; i < SIZE / 2; i++) {
            for (int j = 0; j < SIZE; j++) {
                int temp = p[i][j];
                this.p[i][j] = p[SIZE - i - 1][j];
                this.p[SIZE - i - 1][j] = temp;
            }
        }
    }

    //左右翻转
    public void _rotate180() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE / 2; j++) {
                int temp = p[i][j];
                this.p[i][j] = p[i][SIZE - j - 1];
                this.p[i][SIZE - j- 1] = temp;
            }
        }
    }

    public void show() {
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(p[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private int generate2or4() {
        return (random.nextInt(3) % 2 == 1) ? 4 : 2;
    }

    public void setARandNumberInPane() {
        int i,j;
        do {
            i = random.nextInt(4);
            j = random.nextInt(4);
        } while (p[i][j] != 0);
        p[i][j] = generate2or4();
    }

    //假设从右向左开始计算碰撞 如果两个数相等或着其中有个数为0左数乘2右边全部左移一位 如果不等保持不变
    public void collisionAlgorithm() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = SIZE - 1; j > 0; j--) {
                if(p[i][j - 1] == 0) {
                    int k = j - 1;
                    for (; k < SIZE - 1; k++) {
                        p[i][k] = p[i][k + 1];
                    }
                    p[i][k] = 0;
                } else if (p[i][j - 1] == p[i][j]) {
                    p[i][j - 1] *= 2;
                    int k = j;
                    for (; k < SIZE - 1; k++) {
                        p[i][k] = p[i][k + 1];
                    }
                    p[i][k] = 0;
                }
            }
        }
    }
}
