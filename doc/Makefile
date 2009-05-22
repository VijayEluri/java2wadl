all:
	make translate
	make show

translate:
	bibtex index
	latex index.tex
	latex index.tex
	bibtex index
	dvipdf index.dvi

show:
	open index.pdf

clean:
	rm -f *.log
	rm -f *.dvi
	rm -f *.pdf
	rm -f *.ps
	rm -f *.aux
	rm -f *.gz
	rm -f *.out
	rm -f *.bbl
	rm -f *.blg
	rm -f *.toc

print:
	pdf2ps index.pdf
	lpr -PDeskJet-970C index.ps
