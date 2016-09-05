
(ns ssr-stages.core
  (:require [cljs.reader :refer [read-string]]
            [respo.core :refer [render! clear-cache! falsify-stage!]]
            [respo.util.format :refer [rigidify-element]]
            [ssr-stages.comp.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]))

(defn dispatch! [op op-data])

(defonce store-ref (atom {}))

(defonce states-ref (atom {}))

(defn render-app! []
  (let [target (.querySelector js/document "#app")]
    (render!
      (comp-container @store-ref (hash-set :shell :dynamic))
      target
      dispatch!
      states-ref)))

(defn on-jsload []
  (clear-cache!)
  (render-app!)
  (println "code update."))

(def ssr-stages
 (let [ssr-element (.querySelector js/document "#ssr-stages")
       ssr-markup (.getAttribute ssr-element "content")]
   (read-string ssr-markup)))

(defn -main []
  (enable-console-print!)
  (if (not (empty? ssr-stages))
    (falsify-stage!
      (rigidify-element (comp-container @store-ref ssr-stages))))
  (render-app!)
  (add-watch store-ref :changes render-app!)
  (add-watch states-ref :changes render-app!)
  (println "app started!"))

(set! (.-onload js/window) -main)
