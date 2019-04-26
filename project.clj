(defproject appy "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://github.com/damienstanton/appy"
  :license {:name "MIT"
            :url "https://www.opensource.org/licenses/mit/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring "1.7.1"]
                 [compojure "1.6.1"]]
  :repl-options {:init-ns appy.core}
  :main appy.core
  :profiles {:dev
             {:main appy.core/-dev}})