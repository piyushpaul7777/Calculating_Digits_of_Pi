import java.util.ArrayList;
import java.util.List;

class Block {
    double position;
    double velocity;

    Block(double position, double velocity) {
        this.position = position;
        this.velocity = velocity;
    }
}

public class PiApproximation {
    public static void main(String[] args) {
        int blockLength = 1000;
        double initialVelocity = 1.0;
        List<Block> blocks = new ArrayList<>();

        for (int i = 0; i < blockLength; i++) {
            blocks.add(new Block(i, initialVelocity));
        }

        double minCollisionTime = Double.MAX_VALUE;
        int firstBlock = -1, secondBlock = -1;

        for (int i = 0; i < blocks.size(); i++) {
            for (int j = i + 1; j < blocks.size(); j++) {
                Block b1 = blocks.get(i);
                Block b2 = blocks.get(j);

                double collisionTime = (b2.position - b1.position) / (b1.velocity - b2.velocity);

                if (collisionTime > 0 && collisionTime < minCollisionTime) {
                    minCollisionTime = collisionTime;
                    firstBlock = i;
                    secondBlock = j;
                }
            }
        }

        Block b1 = blocks.get(firstBlock);
        Block b2 = blocks.get(secondBlock);
        double v1 = b1.velocity;
        double v2 = b2.velocity;

        b1.velocity = ((b1.position - b2.position) * (v1 - v2) + 2 * v2) / (blockLength - 1);
        b2.velocity = ((b2.position - b1.position) * (v2 - v1) + 2 * v1) / (blockLength - 1);

        System.out.println("Estimated value of π: " + estimatePi(blockLength));
    }

    private static double estimatePi(int blockLength) {
        return Math.sqrt((double) (blockLength - 1) * 6 / Math.PI);
    }
}
