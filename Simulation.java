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

public class Simulation {
    public static void main(String[] args) {
        // Constants
        final int blockLength = 100;
        final double initialVelocity = 1.0;

        // Initialize blocks
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < blockLength; i++) {
            blocks.add(new Block(i, initialVelocity));
        }

        // Simulation parameters
        int numCollisions = 0;
        double minCollisionTime = Double.POSITIVE_INFINITY;
        int firstBlock = -1, secondBlock = -1;

        // Simulation loop
        while (numCollisions < blockLength - 1) {
            // Find the next collision
            for (int i = 0; i < blocks.size(); i++) {
                for (int j = i + 1; j < blocks.size(); j++) {
                    Block b1 = blocks.get(i);
                    Block b2 = blocks.get(j);

                    // Time to collision
                    double collisionTime = (b2.position - b1.position) / (b1.velocity - b2.velocity);
                    if (collisionTime > 0 && collisionTime < minCollisionTime) {
                        minCollisionTime = collisionTime;
                        firstBlock = i;
                        secondBlock = j;
                    }
                }
            }

            // Check if collisions were found
            if (firstBlock != -1 && secondBlock != -1) {
                // Handle collision between firstBlock and secondBlock
                Block b1 = blocks.get(firstBlock);
                Block b2 = blocks.get(secondBlock);
                double v1 = b1.velocity;
                double v2 = b2.velocity;
                b1.velocity = ((b1.position - b2.position) * (v1 - v2) + v1 + v2) / (blockLength - 1);
                b2.velocity = ((b2.position - b1.position) * (v2 - v1) + v2 + v1) / (blockLength - 1);
                
                numCollisions++;
            }
        }

        // Print final velocities
        for (Block block : blocks) {
            System.out.println("Block position: " + block.position + ", Final velocity: " + block.velocity);
        }
    }
}
