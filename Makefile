# Nome completo da classe principal (pacote.Classe)
MAIN_CLASS = grafo.Grafo

SOURCES = grafo/*.java

all:
	javac -encoding UTF-8 $(SOURCES)

run:
	java $(MAIN_CLASS)

clean:
	rm -f grafo/*.class