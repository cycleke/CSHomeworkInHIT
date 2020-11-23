(TeX-add-style-hook
 "main"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-class-options
                     '(("article" "12pt" "onecolumn")))
   (TeX-add-to-alist 'LaTeX-provided-package-options
                     '(("xeCJK" "BoldFont" "SlantFont") ("ntheorem" "amsmath" "thmmarks" "hyperref") ("newtxtext" "defaultsups") ("footmisc" "bottom" "perpage" "hang") ("natbib" "sort&compress") ("placeins" "below") ("ccaption" "subfigure") ("algorithm2e" "algoruled" "linesnumbered")))
   (add-to-list 'LaTeX-verbatim-environments-local "lstlisting")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "lstinline")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "href")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "hyperref")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "hyperimage")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "hyperbaseurl")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "nolinkurl")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "url")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "path")
   (add-to-list 'LaTeX-verbatim-macros-with-delims-local "lstinline")
   (add-to-list 'LaTeX-verbatim-macros-with-delims-local "path")
   (TeX-run-style-hooks
    "latex2e"
    "article"
    "art12"
    "xeCJK"
    "amsmath"
    "ntheorem"
    "amssymb"
    "newtxtext"
    "newtxmath"
    "courier"
    "graphicx"
    "pdfpages"
    "enumitem"
    "environ"
    "footmisc"
    "pifont"
    "longtable"
    "booktabs"
    "natbib"
    "hyperref"
    "geometry"
    "fancyhdr"
    "tabularx"
    "varwidth"
    "changepage"
    "multicol"
    "multienum"
    "placeins"
    "subfigure"
    "ccaption"
    "siunitx"
    "bm"
    "mathrsfs"
    "xcolor"
    "rotating"
    "algorithm2e"
    "listings"
    "tikz"
    "indentfirst"
    "float"
    "diagbox")
   (TeX-add-symbols
    '("theUndirectedEdge" 2)
    '("theDirectedEdge" 2)
    '("theNode" 1)
    '("theNetwork" 1)
    '("theUndirected" 1)
    '("theDirected" 1)
    '("theSet" 1)
    '("theMatrix" 1)
    '("theVector" 1)
    "hei"
    "song"
    "fs"
    "kai"
    "li"
    "xw")
   (LaTeX-add-ntheorem-newtheorems
    "assumption"
    "definition"
    "proposition"
    "lemma"
    "theorem"
    "axiom"
    "corollary"
    "exercise"
    "example"
    "remark"
    "problem"
    "conjecture"
    "solution")
   (LaTeX-add-xcolor-definecolors
    "colorzero"
    "colorone"
    "colortwo"
    "colorthree"))
 :latex)

