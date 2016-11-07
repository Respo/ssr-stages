
(ns ssr-stages.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.alias :refer [create-comp div span]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [ssr-stages.style.widget :as widget]))

(def style-shell {:color (hsl 0 0 100), :background-color (hsl 120 80 60), :height 160})

(def style-zero {:color (hsl 0 0 100), :background-color (hsl 0 80 60), :height 160})

(def style-dynamic {:color (hsl 0 0 100), :background-color (hsl 240 80 60), :height 160})

(defn render [store ssr-stages]
  (fn [state mutate!]
    (div
     {:style (merge widget/global)}
     (div
      {:style style-zero, :event {:click (fn [e dispatch!] (println "click on zero!"))}}
      (comp-text "Zero Content" nil))
     (if (contains? ssr-stages :shell)
       (div
        {:style style-shell, :event {:click (fn [e dispatch!] (println "click on shell!"))}}
        (comp-text "Shell Content" nil)))
     (if (contains? ssr-stages :dynamic)
       (div
        {:style style-dynamic,
         :event {:click (fn [e dispatch!] (println "click on dynamic!"))}}
        (comp-text "Dynamic Content" nil))))))

(def comp-container (create-comp :container render))
