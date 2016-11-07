
(ns ssr-stages.main
  (:require [cljs.reader :refer [read-string]]
            [respo.core :refer [render! clear-cache! falsify-stage! render-element]]
            [ssr-stages.comp.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]
            [devtools.core :as devtools]))

(defn dispatch! [op op-data] )

(defonce store-ref (atom {}))

(defonce states-ref (atom {}))

(defn render-app! []
  (let [target (.querySelector js/document "#app")]
    (render!
     (comp-container @store-ref (hash-set :shell :dynamic))
     target
     dispatch!
     states-ref)))

(def ssr-stages
  (let [ssr-element (.querySelector js/document "#ssr-stages")
        ssr-markup (.getAttribute ssr-element "content")]
    (read-string ssr-markup)))

(defn -main! []
  (enable-console-print!)
  (devtools/install!)
  (if (not (empty? ssr-stages))
    (let [target (.querySelector js/document "#app")]
      (falsify-stage!
       target
       (render-element (comp-container @store-ref ssr-stages) states-ref)
       dispatch!)))
  (render-app!)
  (add-watch store-ref :changes render-app!)
  (add-watch states-ref :changes render-app!)
  (println "app started!"))

(defn on-jsload! [] (clear-cache!) (render-app!) (println "code update."))

(set! (.-onload js/window) -main!)
