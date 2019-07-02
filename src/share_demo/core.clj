(ns share-demo.core
  (:require
    [clj-http.client :as client]
    [clojure.string :as str]
    [clojure.data.csv :as csv]
    [clojure.java.io :as io]
    ))


(defn get-share-data
  "Given an api key for the worldtradingdata api and a share ticker symbol gets the latest data for the share"
  [api-key s]
  (let [result (client/get "https://api.worldtradingdata.com/api/v1/stock"
                           {:query-params {:symbol    (str/join "," s)
                                           :api_token api-key}
                            :as           :json})]
    (get-in result [:body :data]))
  )

(defn read-portfolio
  "Reads in a portfolio file, csv with share ticker symbol and quantity as a string on each row"
  [filename]
  (with-open [reader (io/reader filename)]
    (doall
      (csv/read-csv reader)))
  )

(defn position-value
  "Calculates the value of a share taking the api-key and a tuple of the ticker and quantity owned for a share"
  [api-key [ticker quantity]]
  (let [share-data (first (get-share-data api-key [ticker]))]
    (* (Double/parseDouble (:price share-data)) (Integer/parseInt quantity))
    )
  )

(defn value-portfolio [filename api-key]
  "Calculates the value of the share portfolio in a csv file, using the worldtrading api"
  (let [portfolio (read-portfolio filename)]
    (reduce + (map (partial position-value api-key) portfolio))
    )
  )