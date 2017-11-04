JAVAC=javac
JFLAGS=-cp bin/ -sourcepath src -d bin
JAR=jar

CLASSES= bin/baccarat/Card.class bin/baccarat/Deck.class bin/baccarat/Game.class bin/baccarat/Player.class bin/baccarat/Baccarat.class bin/baccarat/BaccaratGame.class bin/baccarat/BaccaratPlayer.class
OUTPUT=Baccarat.jar

all: $(OUTPUT)

bin/baccarat/Card.class:
	$(JAVAC) $(JFLAGS) src/baccarat/Card.java

bin/baccarat/Deck.class:
	$(JAVAC) $(JFLAGS) src/baccarat/Deck.java

bin/baccarat/Game.class:
	$(JAVAC) $(JFLAGS) src/baccarat/Game.java

bin/baccarat/Player.class:
	$(JAVAC) $(JFLAGS) src/baccarat/Player.java

bin/baccarat/Baccarat.class:
	$(JAVAC) $(JFLAGS) src/baccarat/Baccarat.java

bin/baccarat/BaccaratGame.class:
	$(JAVAC) $(JFLAGS) src/baccarat/BaccaratGame.java

bin/baccarat/BaccaratPlayer.class:
	$(JAVAC) $(JFLAGS) src/baccarat/BaccaratPlayer.java

Baccarat.jar: $(CLASSES)
	cp -r src/baccarat/images bin/baccarat/
	jar cvfe Baccarat.jar baccarat.Baccarat -C bin/ .

clean:
	rm $(OUTPUT)
	rm $(CLASSES)
	rm -rf bin/*
