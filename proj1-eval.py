#! /usr/bin/python

def getStr(name, msg, default):
    print 'Default %s: %s'%(name, default)
    result = raw_input('%s (leave blank for default): '%msg)
    if result == "":
        result = default
    print 'Using %s: %s'%(name, result)
    print
    return result

invokation = getStr("invokation", "Invokation", "java -cp ./bin cs213.photoAlbum.simpleview.CmdView")
input = getStr("input", "Input file", "proj1-eval.txt")
wait = float(getStr("wait time", "Seconds to wait before feeding the next line", ".3"))
scorecsv = getStr("output csv", "Output file to put a summary csv", "scores.csv")

lines = [line.strip() for line in open(input).readlines()]

scores = []

totalPoints, maxPoints = 0, 0

currentNumber = 0
currentMax = 0

import subprocess, time

currentProc = None

def invoke(args):
    print '$', args
    #raw_input('===hit return===')
    global currentProc
    try:
	currentProc = subprocess.Popen(['sh', '-c', invokation+' '+args], stdin=subprocess.PIPE)
    except:
	print '-------> EXCEPTION <------------'
def kill():
    time.sleep(wait)
    if currentProc and currentProc.poll() == None:
        print 'killing'
        currentProc.terminate()
def cmdline(text):
    time.sleep(wait)
    try:
        currentProc2 = subprocess.Popen(['sh', '-c', '"' + text + '"'], stdin=subprocess.PIPE)
    except:
        print '--------> EXCEPTION <------------'
def input(text):
    time.sleep(wait)
    print '>', text
    #raw_input('===hit return===')
    try:
        currentProc.stdin.write(text+'\n')
    except:
        print '-------> EXCEPTION <------------'

def finish():
    global currentNumber, currentMax, scores, totalPoints, maxPoints
    kill()
    score = currentMax+1
    while score > currentMax:
        scorestr = raw_input('Score for problem %d (out of %d):'%(currentNumber, currentMax))
        try:
            score = int(scorestr)
        except:
            print 'Could not turn %s into an integer'%scorestr
        if score > currentMax:
            print 'Maximum is %d'%currentMax
    scores += [(currentNumber, score, currentMax)]
    totalPoints += score
    maxPoints += currentMax
    currentNumber = 0
   

for line in lines:
    if line == '':
        continue
    if line[:2] == '//':
        print line
        continue
    if line[0] == '#':
        if currentNumber != 0:
            finish()
        [nstr, pstr] = line[1:].split()
        currentNumber, currentMax = int(nstr), int(pstr)
	print '=============================================================='
	print '                  TestCase : %d'%(currentNumber)
	print '=============================================================='
    if line[0] == '$':
        kill()
        args = line[1:]
        invoke(args.strip())
    if line[0] == '>':
        text = line[1:].strip()
        input(text)
    if line[0] == '*':
	text = line[1:].strip()
	cmdline(text)
if currentNumber != 0:
    finish()

outcsv = open(scorecsv, 'w')

outcsv.write('problem, score, max\n')
outcsv.write('total, %d, %d\n'%(totalPoints, maxPoints))
for score in scores:
    outcsv.write('%d, %d, %d\n'%score)

print "Summary written to %s"%scorecsv    
