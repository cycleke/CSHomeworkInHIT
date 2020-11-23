import os

with open("results.txt", "a") as file:
    for i in range(1, 17):
        file.write("\n\n####TEST%02d" % i)
        file.flush()
        os.system("make test%02d >> results.txt" % i)
        file.write("\n")
        file.flush()
        os.system("make rtest%02d >> results.txt" % i)
