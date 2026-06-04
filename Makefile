# Nome completo da classe principal (pacote.Classe)
MAIN_CLASS = grafos.Main

SOURCES = grafos/*.java

all:
	javac -encoding UTF-8 $(SOURCES)

run:
	java $(MAIN_CLASS)

clean:
	rm -f grafos/*.class