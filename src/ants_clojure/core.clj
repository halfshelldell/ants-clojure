(ns ants-clojure.core
  (:require [clojure.java.io :as io])
  (:gen-class :extends javafx.application.Application))

(def width 800)
(def height 600)
(def ant-count 100)
(def ants (atom []))

(defn create-ants []
  (for [i (range 0 ant-count)]
    {:x(rand-int width)
     :y (rand-int height)}))
  
(defn draw-ants! [context]
  (.clearRect context 0 0 width height)
  (doseq [ant @ants]
    (.setFill context javafx.scene.paint.Color/BLACK)
    (.fillOval context (:x ant) (:y ant) 5 5)))

(defn -start [app stage]
  (let [root (javafx.fxml.FXMLLoader/load (io/resource "main.fxml"))
        scene (javafx.scene.Scene. root width height)
        canvas (.lookup scene "#canvas")
        context (.getGraphicsContext2D canvas)]
    (.setTitle stage "Ants")
    (.setScene stage scene)
    (.show stage)
    (reset! ants (create-ants))
    (draw-ants! context)))


(defn -main []
  (javafx.application.Application/launch ants_clojure.core (into-array String [])))
