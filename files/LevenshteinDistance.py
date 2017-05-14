import math
class levenshteinDistance:
    __title = "levenshtein"
    def compute(self,dns,inputDns):
        if dns == inputDns:
            return 0
        if len(dns) == 0:
            return len(inputDns)
        if len(inputDns) == 0:
            return len(dns)
        v0 = range(len(inputDns)+1)
        for i in range(len(dns)):       
            v1=[i+1]
            for j in range(len(inputDns)):
                cost = 0 if dns[i] == inputDns[j] else 1
                v1.append(min(v1[j]+1,v0[j+1],v0[j]+cost))
            for l in range(len(v0)):
                v0[l]=v1[l]
        return v1[len(inputDns)]
    if __name__ == "__main__":
        print 1
     
        
                
            

