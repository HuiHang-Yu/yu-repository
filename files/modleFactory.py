#-----*---encoding----*-----
#this python file is written to create the module for libsvm or AdaBoost
from LevenshteinDistance import levenshteinDistance

import urllib,urllib2,socket,re

class modleFactory:

    __title = 'modulefactory'

    __list = []

    __leven = levenshteinDistance()

    def __init__(self):
        self.getWhiteList()

    def getWhiteList(self):
        file = open("white.txt",'r')
        for line in file.readlines():
            _list.append(line)
        file.close()
        file = open("black.txt",'r')
        for line in file.readlines():
            _list.append(line)
        file.close()

    def getDNSLen(self,dns):
        return dns.split(r'.').lenth

    def getDNSType(self,dns):
        return 0 if re.match(r'^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$',dns)==None else 1

    def getDNSDistance(self,url):
        cost = 0
        flag = False
        for dns in __list:
            cost = __leven(dns,url)
            if cost <3 and cost > 0:
                flag = True
                break;
        return flag

    def getDNSLiveTime(self,dns):
        payload = {"t":"whoisupd","callback":"Query111306185286934991756_1494484932748","host":dns,"type":"0","ws":"whois.enom.com"}
        url = r'''http://whois.chinaz.com/update.ashx'''
      	socket.setdefaulttimeout(50)
        print resp

    if __name__ == '__main__':
        l = levenshteinDistance()
        dns = 'www.baidu.com'
        socket.setdefaulttimeout(50)
        payload = {"t":"whoisupd","callback":"Query111306185286934991756_1594484932748","host":dns,"type":"0","ws":"whois.enom.com"}
        i_headers = {"User-Agent": "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1) Gecko/20090624 Firefox/3.5",  
             "Accept": "text/plain"}
        url = r'''http://whois.chinaz.com/update.ashx'''
        req = urllib2.Request(url, data=urllib.urlencode(payload), headers=i_headers)
        try:  
            page = urllib2.urlopen(req)
        except urllib2.HTTPError, e:  
            print "Error Code:", e.code  
        except urllib2.URLError, e:  
            print "Error Reason:", e.reason  
        r = re.compile('createdata:\'(\d{4}-\d{1,2}-\d{1,2})\'')
        pagedata = page.read()
        print pagedata
        print r.findall(pagedata)
        
    
    
