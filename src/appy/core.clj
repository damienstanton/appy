(ns appy.core
  (:require [ring.adapter.jetty :as j]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.handler.dump :refer [handle-dump]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]))

(defn peace [req]
  {:status 200
   :body "Later bruh"
   :headers {}})

(defn none [req]
  {:status 404
   :body "Uhh"
   :headers {}})

(defn greet [req]
  {:status 200
   :body "Hey"
   :headers {}})

(defn named [req]
  (let [name (get-in req [:route-params :name])]
    {:status 200
     :body (str "Hi, " name ". I bet you are cool")
     :headers {}}))

(defn calc [req]
  (let [a (Integer. (get-in req [:route-params :a]))
        b (Integer. (get-in req [:route-params :b]))
        op (get-in req [:route-params :op])
        ops {"+" +
             "-" -
             ":" /
             "*" *}
        f (get ops op)]
    (if f
      {:status 200
       :body (str (f a b))
       :headers {}}
      {:status 500
       :body (str "Yo wtf is " op)
       :headers {}})))

(defroutes app
  (GET "/" [] greet)
  (GET "/hi/:name" [] named)
  (GET "/peace" [] peace)
  (GET "/request" [] handle-dump)
  (GET "/calc/:op/:a/:b" [] calc)
  (not-found none))

(defn -main [port]
  (j/run-jetty app
               {:port (Integer. port)}))

(defn -dev [port]
  (j/run-jetty (wrap-reload #'app)
               {:port (Integer. port)}))