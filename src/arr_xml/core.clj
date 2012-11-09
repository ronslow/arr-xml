
(ns arr-xml.core)

(use 'conduit.core 'arrows.core 'arrows-extra.core 'clojure.data.xml)


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
