# Calculating_Digits_of_Pi
Project Description: Calculating Digits of π with Collisions
Overview:
This project aims to approximate the digits of π using a method based on counting collisions
between two colliding blocks. It involves simulating one-dimensional collisions and
implementing a heap data structure for efficient computation.
Components:
1. Simulation of Collisions:
o Objective: Simulate collisions between blocks moving in one dimension.
o Implementation: Use a physics-based simulation where blocks move with
constant velocities and bounce off each other upon collision.
o Details: Capture positions and velocities of blocks over time. Detect collisions
and compute π based on collision statistics.
2. Heap Data Structure:
o Objective: Implement a heap to manage events (collisions) efficiently.
o Implementation: Design a min-heap to prioritize collision events based on
their timestamps.
o Details: Ensure insertion and extraction operations maintain the heap
property, allowing efficient processing of collision events.
3. Calculating Digits of π:
o Objective: Use collision data to estimate the value of π.
o Implementation: Count the total number of collisions and compute π using a
formula that relates collisions to π's digits.
o Details: Ensure accuracy and efficiency in computing π by aggregating
collision data and applying mathematical formulas.
Project Overview
The goal of this project is to simulate the behavior of blocks colliding elastically in a onedimensional space. The simulation uses principles of classical mechanics and conservation
laws to model the collisions accurately. Here’s a breakdown of the logic and mathematics
involved:
1. Block Representation
Each block is represented by two main properties:
• Position (position): Represents the current position of the block along a straight
line.
• Velocity (velocity): Indicates the speed and direction at which the block is moving.
2. Initialization
The simulation begins by initializing a list of blocks (ArrayList<Block>). Each block is
given a unique position starting from 0 and an initial velocity (initialVelocity). For
simplicity, all blocks start with the same initial velocity.
java
Copy code
List<Block> blocks = new ArrayList<>();
for (int i = 0; i < blockLength; i++) {
 blocks.add(new Block(i, initialVelocity));
}
3. Collision Detection
The core of the simulation is to detect when and where collisions occur between pairs of
blocks. This is achieved through nested loops that iterate over each pair of blocks to find the
earliest collision based on their current velocities and positions.
Collision Time Calculation
For two blocks b1b1b1 and b2b2b2 positioned at x1x1x1 and x2x2x2 respectively, with
velocities v1v1v1 and v2v2v2, the time ttt until they collide can be calculated using the
formula derived from their relative motion:
t=x2−x1v1−v2t = \frac{x2 - x1}{v1 - v2}t=v1−v2x2−x1
This formula assumes elastic collisions where kinetic energy is conserved.
Finding the Next Collision
The simulation iterates over all pairs of blocks to find the smallest positive collision time ttt.
This time represents the next collision between two blocks.
java
Copy code
for (int i = 0; i < blocks.size(); i++) {
 for (int j = i + 1; j < blocks.size(); j++) {
 Block b1 = blocks.get(i);
 Block b2 = blocks.get(j);
 // Calculate time to collision
 double collisionTime = (b2.position - b1.position) / (b1.velocity -
b2.velocity);
 // Update minCollisionTime and block indices if this collision time
is smaller
 if (collisionTime > 0 && collisionTime < minCollisionTime) {
 minCollisionTime = collisionTime;
 firstBlock = i;
 secondBlock = j;
 }
 }
}
4. Collision Handling
Once the next collision is identified, the simulation updates the velocities of the colliding
blocks according to the laws of conservation of momentum and energy:
Velocity Update
For blocks b1b1b1 and b2b2b2:
• Velocity Update Formula: v1′=(v1−v2)⋅(x1−x2)+2⋅m2⋅v2m1+m2v1' = \frac{(v1 -
v2) \cdot (x1 - x2) + 2 \cdot m2 \cdot v2}{m1 +
m2}v1′=m1+m2(v1−v2)⋅(x1−x2)+2⋅m2⋅v2 v2′=(v2−v1)⋅(x2−x1)+2⋅m1⋅v1m1+m2v2'
= \frac{(v2 - v1) \cdot (x2 - x1) + 2 \cdot m1 \cdot v1}{m1 +
m2}v2′=m1+m2(v2−v1)⋅(x2−x1)+2⋅m1⋅v1
Where m1m1m1 and m2m2m2 are masses (assumed equal here), x1x1x1 and x2x2x2
are positions, v1v1v1 and v2v2v2 are velocities before collision, and v1′v1'v1′ and
v2′v2'v2′ are velocities after collision.
java
Copy code
Block b1 = blocks.get(firstBlock);
Block b2 = blocks.get(secondBlock);
double v1 = b1.velocity;
double v2 = b2.velocity;
// Update velocities based on elastic collision formulas
b1.velocity = ((b1.position - b2.position) * (v1 - v2) + 2 * v2) /
(blockLength - 1);
b2.velocity = ((b2.position - b1.position) * (v2 - v1) + 2 * v1) /
(blockLength - 1);
5. Simulation Termination and Output
The simulation continues until all possible collisions have been resolved (numCollisions
equals blockLength - 1). Afterward, the final positions and velocities of all blocks are
printed to observe the distribution and behavior of the blocks after multiple collisions.
Mathematical Basis
• Elastic Collisions: The program assumes elastic collisions where kinetic energy is
conserved. This assumption simplifies the velocity update calculations and ensures
that the simulation reflects physical principles accurately within the specified model.
• Simulation Precision: The precision of the simulation depends on the number of
blocks (blockLength) and the accuracy of the collision detection algorithm. In
practical applications, more complex models may consider factors such as non-elastic
collisions, external forces, and boundary conditions.
Conclusion
This simulation project provides a hands-on approach to understanding fundamental concepts
in classical mechanics and computational physics. By implementing and running the
program, users can explore how simple rules of collision dynamics can lead to emergent
behaviors and patterns, such as the distribution of velocities among blocks over time.
