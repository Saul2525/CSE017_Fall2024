import random

dice = list()

for i in range(6):
    dice.append(random.randint(1, 6))

dice_sum = sum(dice)

print("Rolls:", dice)
print("Sum:", dice_sum)
