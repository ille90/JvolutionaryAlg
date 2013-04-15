# -*- coding: utf-8 -*-
# Jython source file
from jea import GenePool
from jea import Permutation

class Staedte(GenePool):
    def __init__(self):
        self.costs = [[0, 5, 8, 11, 4, 7],
        [5, 0, 10, 4, 9, 12],
        [8, 10, 0, 6, 17, 8],
        [11, 4, 6, 0, 6, 5],
        [4, 9, 17, 6, 0, 11],
        [7, 12, 8, 5, 11, 0]]
    
    def geneCount(self):
        return len(self.costs)
    
    def calcFitness(self, permutation):
        costsCount = 0.0
        for i in range(self.geneCount()):
            nextGene = 0
            if (i + 1) < self.geneCount():
                nextGene = i + 1
            costsCount = costsCount + self.costs[permutation.getGene(i)][permutation.getGene(nextGene)]
        permutation.setFitness(costsCount)
    
    def printPermutationInfo(self, permutation):
        print "Städtefolge: "
        for i in range(self.geneCount()):
            print permutation.getGene(i)
            print ", "
        print permutation.getGene(0)
        print "Kosten: "
        self.calcFitness(permutation)
        print permutation.getFitness()
        print "\n"