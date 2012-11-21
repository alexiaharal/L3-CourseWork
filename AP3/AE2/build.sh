JFLAGS = -cp .
JC = javac
.SUFFIXES: .java .class
.java.class:
        $(JC) $(JFLAGS) $*.java

CLASSES = \
        fileCrawler.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class
