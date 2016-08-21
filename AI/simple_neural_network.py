from numpy import array, random, exp, dot

class NeuralNetwork():
    def __init__(self):
        random.seed(1)
        # matrix:3*1, value:[-1, 1]
        self.synaptic_weight = 2*random.random((3,1)) -1
    
    def __sigmoid(self, x):
        return 1/(1 + exp(-x))

    def __sigmoid_deravitive(self, x):
        return x*(1-x)

    def think(self, input_set):
        return self.__sigmoid(dot(input_set, self.synaptic_weight))

    def training(self, input_training, output_training, iterations):
        for i in range(iterations):
            output = self.think(input_training)
            error = output_training - output
            #权重调整公式??
            adjustment = dot(input_training.T, error*self.__sigmoid_deravitive(output))
            self.synaptic_weight += adjustment

    def test(self):
        print("Random weight: ")
        print(self.synaptic_weight)

        input_set = array([[0,0,1], [1,1,1], [1,0,1], [0,1,1],[0,1,0]])
        # .T 转置矩阵
        output_set = array([[0,1,1,0,0]]).T
        print(input_set)
        print(output_set)
        self.training(input_set, output_set, 10000)
        print("New synaptic weight:")
        print(self.synaptic_weight)
        
        print("Result: ")
        print(self.think(array([1,0,0])))
        print(self.think(array([1,1,0])))

if __name__ == "__main__":
    neuralNetwork = NeuralNetwork()

    neuralNetwork.test()
