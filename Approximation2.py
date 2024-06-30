class Block:
    def __init__(self, position, velocity):
        self.position = position
        self.velocity = velocity

def simulate_collisions(block_length, initial_velocity):
    blocks = [Block(i, initial_velocity) for i in range(block_length)]
    num_collisions = 0

    while True:
        min_collision_time = float('inf')
        first_block = -1
        second_block = -1

        for i in range(len(blocks)):
            for j in range(i + 1, len(blocks)):
                b1 = blocks[i]
                b2 = blocks[j]
                if b1.velocity != b2.velocity:  # avoid division by zero
                    collision_time = (b2.position - b1.position) / (b1.velocity - b2.velocity)
                    if 0 < collision_time < min_collision_time:
                        min_collision_time = collision_time
                        first_block = i
                        second_block = j

        if min_collision_time == float('inf'):
            break

        for block in blocks:
            block.position += block.velocity * min_collision_time

        b1 = blocks[first_block]
        b2 = blocks[second_block]
        v1 = b1.velocity
        v2 = b2.velocity

        # Update velocities based on elastic collision formulas
        b1.velocity = ((b1.position - b2.position) * (v1 - v2) + 2 * v2) / (block_length - 1)
        b2.velocity = ((b2.position - b1.position) * (v2 - v1) + 2 * v1) / (block_length - 1)

        num_collisions += 1

        print(f"Collision {num_collisions}: {min_collision_time}")

    print(f"Total Collisions: {num_collisions}")

if __name__ == "__main__":
    block_length = 100
    initial_velocity = -1.0
    simulate_collisions(block_length, initial_velocity)
