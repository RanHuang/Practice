import numpy
import matplotlib.pyplot as plot

x = numpy.arange(0,6,0.1)
y1 = numpy.sin(x)
y2 = numpy.cos(x)

plot.plot(x, y1, label="sin")
plot.plot(x, y2, label="cos", linestyle="--")
plot.xlabel("x")
plot.ylabel("y")
plot.title('sin & cos')
plot.legend()

plot.show()
