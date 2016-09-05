
(ns ssr-stages.boot
  (:require
    [respo.alias :refer [html head title script style meta' div link body]]
    [respo.render.static-html :refer [make-html make-string]]
    [ssr-stages.comp.container :refer [comp-container]]
    [planck.core :refer [spit]]))

(defn use-text [x] {:attrs {:innerHTML x}})
(defn html-dsl [html-content]
  (make-html
    (html {}
    (head {}
      (title (use-text "Stack Workflow"))
      (link {:attrs {:rel "icon" :type "image/png" :href "mvc-works-192x192.png"}})
      (meta' {:attrs {:charset "utf-8"}})
      (meta' {:attrs {:name "viewport" :content "width=device-width, initial-scale=1"}})
      (meta' {:attrs {:id "ssr-stages" :content "#{}"}})
      (style (use-text "body {margin: 0;}"))
      (style (use-text "body * {box-sizing: border-box;}"))
      (script {:attrs {:id "config" :type "text/edn" :innerHTML (pr-str {})}}))
    (body {}
      (div {:attrs {:id "app" :innerHTML html-content}})
      (script {:attrs {:src "main.js"}})))))

(defn generate-html [ssr-stages]
  (let [ tree (comp-container {} ssr-stages)
         html-content (make-string tree)]
    (html-dsl html-content)))

(defn -main []
  (spit "target/shell.html" (generate-html #{:shell}))
  (spit "target/dynamic.html" (generate-html #{:shell :dynamic})))

(-main)