import matplotlib.pyplot as plot
import matplotlib.image as image 

# read the image
img = image.imread('lena.png')
plot.imshow(img)

plot.show()
