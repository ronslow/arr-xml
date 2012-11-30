
(ns arr-xml.core)

(use 'conduit.core 'arrows.core 'arrows-extra.core 'clojure.data.xml)

(defn arr-switch-elem [default-channel name channel & more]
 (arr-toggle-switch-inclusive (if (empty? more) default-channel (apply (partial arr-switch-elem default-channel) more)) (fn[input] (and (= (:name input) name) (= (:type input) :start-element)))
                                           (fn[input] (and (= (:name input) name) (= (:type input) :end-element)))
                                           channel
 )
)
(defn arr-select-elem [name]
   (arr-switch-elem (a-arr (fn [input] nil)) name pass-through)
  )



(defn arr-select-path [name & more]
 (a-comp (arr-select-elem name) (if (empty? more) pass-through (apply arr-select-path more)))
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
