(ns arr-xml.core (:use conduit.core arrows.core arrows-extra.core clojure.data.xml) (:require  [clojure.xml :as xml]))




    
   
  

(defn arr-select-elem [name]
  "Filters out everything except name and its descendants"
  
  (a-comp
   (arr-toggle-inclusive (fn [input]
                 (and (= (:name input) name) (= (:type input) :start-element)))
               (fn [input]
                 
                (and (= (:name input) name) (= (:type input) :end-element))
                
                )
               )
   (a-select true pass-through false block)
   )
  )



(def-proc arr-characters [input]

  (let [chars (:str input)]
    (if chars [chars] [])
    )
  )

(def-proc arr-attributes [input]
  "Outputs the attributes from start elements"
  (let [attrs (:attrs input)]
    (if attrs [attrs] [])
    )
  )


(def-arr arr-stream-to-xml-seq [instream]
  ":: InputStream -> [XML]"
  (parse instream :coalescing false)

  )
(def-arr arr-stream-to-xml-eager [instream]
":: InputStrem -> [xml]"
(xml/parse instream)
  )
