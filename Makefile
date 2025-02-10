all: compile
	@java -cp bin fr.avaj.simulator.Simulator scenario.txt
compile:
	@find * -name "*.java" > sources.txt
	@javac -d bin @sources.txt
clean:
	@rm -rf bin/fr/avaj/simulator/*.class
	@rm -rf sources.txt
re: clean all

.PHONY: all clean re