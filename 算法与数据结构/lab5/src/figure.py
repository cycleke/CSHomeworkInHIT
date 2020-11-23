import csv
import numpy as np
import matplotlib.pyplot as plt

DPI = 480
W, H = 7195, 3841

plt.figure(figsize=(W / DPI, H / DPI), dpi=DPI)

data = []
with open('time.csv', 'r') as f:
    cr = csv.reader(f)
    for row in cr:
        print(row)
        data.append(row)

plt.xlabel("the size of array")
plt.ylabel("time spent")

x = [float(x) for x in data[0][1:]]

for d in data[1:]:
    label = d[0]
    y = [int(y) for y in d[1:]]
    plt.plot(x, y, label=label, linestyle='-', linewidth=.5)

plt.xticks(x, data[0][1:], rotation=270)

plt.legend(loc=0)
plt.savefig('time.png')
# plt.show()
