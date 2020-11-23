CC = gcc
CFLAGS = -m64 -std=c99
LIB = ./lib/vec.c
PROFBIN = pprof

SRCS = $(wildcard *.c)
PROGS = $(patsubst %.c,%,$(SRCS))
PROGS_TEST = $(patsubst %.c,%.test,$(SRCS))

empty:
	@echo Making article ...
	xelatex -shell-escape main.tex
	-bibtex main
	xelatex -shell-escape main.tex
	xelatex -shell-escape main.tex
save:
	mv main.pdf main_beamer.pdf

all: $(PROGS)

%: %.c
	$(CC) $(CFLAGS) -o $@ $< $(LIB)

test: $(PROGS_TEST)

%.test: %
	./$<

5.18.prof:
	gcc -std=c99 -lprofiler 5.18.c -o prof.5.18
	env CPUPROFILE=/tmp/prof.out ./prof.5.18
	$(PROFBIN) prof.5.18 /tmp/prof.out --text

5.19.prof:
	gcc -std=c99 -lprofiler 5.19.c -o prof.5.19
	env CPUPROFILE=/tmp/prof.out ./prof.5.19
	$(PROFBIN) prof.5.19 /tmp/prof.out --text

.PHONY : clean
clean:
	@echo Done.
	-find -name '*.aux' -exec rm -f {} \;
	-find -name '*.bak' -exec rm -f {} \;
	-find -name '*.dvi' -exec rm -f {} \;
	-find -name '*~' -exec rm -f {} \;
	-find -name '#*#' -exec rm -f {} \;
	-find -name 'semantic.cache' -exec rm -f {} \;
	-find -name '*.log' -exec rm -f {} \;
	-rm -f *.bbl *.blg *.log *.out *.ps *.thm *.toc *.toe *.lof *.lot *.nav *.snm *.vrb
	-rm -f *.loa *.aen
	-rm -f *.html *.css *.scm *.hlog
	-rm -f _region_.tex
	-rm -f -rf auto
	-rm -f *.fen
	-rm -f *.ten
	-rm -f *.bcf
	-rm -f *.pyg
	-rm -f *.xml
	-rm -rf *.prv
