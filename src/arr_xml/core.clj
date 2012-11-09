
(ns arr-xml.core)

(use 'conduit.core 'arrows.core 'arrows-extra.core 'clojure.data.xml)

(defn arr-switch-elem [default-channel name channel]
 (arr-toggle-switch-inclusive default-channel (fn[input] (and (= (:name input) name) (= (:type input) :start-element)))
                                           (fn[input] (and (= (:name input) name) (= (:type input) :end-element)))
                                           channel
 )
)
(defn arr-select-elem [name]
   (a-comp (arr-switch-elem name pass-through) block)
  )


(def-proc arr-attributes [input]
  (let [attrs (:attrs input)]
    (if attrs [attrs] [])
    )
  )

(def-proc arr-characters [input]

  (let [chars (:str input)]
    (if chars [chars] [])
    )
  )


(def-arr arr-stream-to-xml-seq [instream]
  ":: InputStream -> [XML]"
  (source-seq instream :coalescing false)

)
