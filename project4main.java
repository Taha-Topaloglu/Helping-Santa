

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class project4main {
	public static void addBagEdges() {
		
	}
	public static void main(String[] args) throws IOException {
		int gt = 0; int rt = 0; int gr = 0; int rr = 0; int totalBags;// Integers for transports and bags
		HashMap<Integer, Integer> greenTrains = new HashMap<Integer, Integer>();  //Hashmaps to store capacities and later using in creating nodes
		ArrayList<Integer> GTrain = new ArrayList<Integer>();					  //Arraylist to store capacities until creating nodes
		HashMap<Integer, Integer> redTrains = new HashMap<Integer, Integer>();
		ArrayList<Integer> RTrain = new ArrayList<Integer>();
		HashMap<Integer, Integer> greenDeers = new HashMap<Integer, Integer>();   
		ArrayList<Integer> GDeer = new ArrayList<Integer>();
		HashMap<Integer, Integer> redDeers = new HashMap<Integer, Integer>();    
		ArrayList<Integer> RDeer = new ArrayList<Integer>();
		
		HashMap<Integer, Integer> BagA = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> BagAB = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> BagAC = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> BagAD = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> BagAE = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> BagABD = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> BagABE = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> BagACD = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> BagACE = new HashMap<Integer, Integer>();
		
		int BagB = 0 ;int BagC = 0;int BagD = 0 ;int BagE = 0 ;int BagBD = 0 ;int BagBE = 0 ;int BagCD = 0 ;int BagCE = 0 ;
		HashMap<Integer, Integer> nodesForNotA = new HashMap<Integer, Integer>();
		for(int i = 0; i < 8; i++) {
			nodesForNotA.put(i, 0);
		}
		
		int currentNode = 1;
		
		//MaxFlowdinic dinic = new MaxFlowdinic();
		
		String inputName = args[0];
		File myInputFile = new File(inputName);
		Scanner read = new Scanner(myInputFile);
		MaxFlowDinic dinic = new MaxFlowDinic();
		gt = read.nextInt();
		if(gt != 0) {
			for(int i = 0; i < gt; i++) {
				GTrain.add(read.nextInt());
			}
		} 
		rt = read.nextInt();
		if( rt != 0) {
			for(int j = 0; j < rt; j++) {
				RTrain.add(read.nextInt());
			}
		}
		gr = read.nextInt();
		if(gr != 0) {
			for(int k = 0; k < gr; k++) {
				GDeer.add(read.nextInt());
			}
		}
		rr = read.nextInt();
		if(rr != 0) {
			for(int x = 0; x < rr; x++) {
				RDeer.add(read.nextInt());
			}
		}

		totalBags = read.nextInt();
		int gifts; int totalGifts = 0;
		for(int y = 0; y < totalBags; y++) {
			
			String brand = read.next();
			String type = brand.trim();
			String number = read.next();
			gifts = Integer.parseInt(number);
			if(type.equals("a")) {
				totalGifts += gifts;
				currentNode += 1;	
				BagA.put(currentNode, gifts);
			}
			else if(type.equals("ab")) {
				totalGifts += gifts;
				currentNode += 1;
				BagAB.put(currentNode, gifts);
			}
			else if(type.equals("ac")) {
				totalGifts += gifts;
				currentNode += 1;
				BagAC.put(currentNode, gifts);
			}
			else if(type.equals("ad")) {
				totalGifts += gifts;
				currentNode += 1;
				BagAD.put(currentNode, gifts);
			}
			else if(type.equals("ae")) {
				totalGifts += gifts;
				currentNode += 1;
				BagAE.put(currentNode, gifts);
			}
			else if(type.equals("abd")) {
				totalGifts += gifts;
				currentNode += 1;
				BagABD.put(currentNode, gifts);
			}
			else if(type.equals("abe")) {
				totalGifts += gifts;
				currentNode += 1;
				BagABE.put(currentNode, gifts);
			}
			else if(type.equals("acd")) {
				totalGifts += gifts;
				currentNode += 1;
				BagACD.put(currentNode, gifts);
			}
			else if(type.equals("ace")) {
				totalGifts += gifts;
				currentNode += 1;
				BagACE.put(currentNode, gifts);
			}
			else if(type.equals("b")) {
				totalGifts += gifts;
				BagB += gifts;
			}
			else if(type.equals("c")) {
				totalGifts += gifts;
				BagC += gifts;
			}
			else if(type.equals("d")) {
				totalGifts += gifts;
				BagD += gifts;
			}
			else if(type.equals("e")) {
				totalGifts += gifts;
				BagE += gifts;
			}	
			else if(type.equals("bd")) {
				totalGifts += gifts;
				BagBD += gifts;
			}
			else if(type.equals("be")) {
				totalGifts += gifts;
				BagBE += gifts;
			}
			else if(type.equals("cd")) {
				totalGifts += gifts;
				BagCD += gifts;
			}
			else if(type.equals("ce")) {	
				totalGifts += gifts;
				BagCE += gifts;
			
			}	else {
				continue;
			}
		}
		
		ArrayList<Integer> bagsNotA = new ArrayList<Integer>(8);

		bagsNotA.add(BagB); bagsNotA.add(BagC); bagsNotA.add(BagD); bagsNotA.add(BagE); bagsNotA.add(BagBD); bagsNotA.add(BagBE); bagsNotA.add(BagCD); bagsNotA.add(BagCE);
		int countNotA = 0;
		for(int i =0; i < bagsNotA.size(); i++) {
			if(bagsNotA.get(i) != 0 && bagsNotA.get(i) != null) {
				countNotA++;
			}
		}
		int totalNodes = countNotA + gt + rt + gr + rr + BagA.size() + BagAB.size() + BagAD.size() + BagAC.size() + BagAE.size() + BagABD.size() + BagABE.size() + BagACD.size() + BagACE.size(); 
		List<MaxFlowDinic.Edge>[] graph = dinic.createGraph(totalNodes + 2);
		
		dinic.fromStoA(graph, BagA); dinic.fromStoA(graph, BagAB); dinic.fromStoA(graph, BagAC); dinic.fromStoA(graph, BagAD); dinic.fromStoA(graph, BagAE); 
		dinic.fromStoA(graph, BagABD); dinic.fromStoA(graph, BagABE); dinic.fromStoA(graph, BagACD); dinic.fromStoA(graph, BagACE);
 		
		int hold = -1;
		for(Integer elem : bagsNotA) {
			hold++;
			if(elem != 0 && elem != null) {
				currentNode += 1;
				dinic.addEdge(graph, 0, currentNode, elem);
				nodesForNotA.replace(hold, currentNode);
			}
		}
		
		
		
		if(gt != 0) {
			for(int i = 0; i < gt; i ++) {
				greenTrains.put(currentNode, GTrain.get(i));
				currentNode += 1;
			}
		}
		if(rt != 0) {
			for(int i = 0; i < rt; i ++) {
				redTrains.put(currentNode, RTrain.get(i));
				currentNode += 1;
			}
		}		
		if(gr != 0) {
			for(int i = 0; i < gr; i ++) {
				greenDeers.put(currentNode, GDeer.get(i));
				currentNode += 1;
			}
		}
		if(rr != 0) {
			for(int i = 0; i < rr; i ++) {
				redDeers.put(currentNode, RDeer.get(i));
				currentNode += 1;
			}
		}
		
		dinic.addBagTypeA(graph, BagA, greenTrains); dinic.addBagTypeA(graph, BagA, redTrains); dinic.addBagTypeA(graph, BagA, greenDeers); dinic.addBagTypeA(graph, BagA, redDeers);
		dinic.addBagTypeA(graph, BagAB, greenTrains); dinic.addBagTypeA(graph, BagAB, greenDeers);
		dinic.addBagTypeA(graph, BagAC, redTrains); dinic.addBagTypeA(graph, BagAC, redDeers);
		dinic.addBagTypeA(graph, BagAD, greenTrains); dinic.addBagTypeA(graph, BagAD, redTrains);
		dinic.addBagTypeA(graph, BagAE, greenDeers); dinic.addBagTypeA(graph, BagAE, redDeers);
		dinic.addBagTypeA(graph, BagABD, greenTrains); dinic.addBagTypeA(graph, BagABE, greenDeers);
		dinic.addBagTypeA(graph, BagACD, redTrains); dinic.addBagTypeA(graph, BagACE, redDeers);
		
		dinic.addBagNotA(graph, nodesForNotA.get(0), greenTrains); dinic.addBagNotA(graph, nodesForNotA.get(0), greenDeers);
		dinic.addBagNotA(graph, nodesForNotA.get(1), redTrains); dinic.addBagNotA(graph, nodesForNotA.get(1), redDeers);
		dinic.addBagNotA(graph, nodesForNotA.get(2), greenTrains); dinic.addBagNotA(graph, nodesForNotA.get(2), redTrains);
		dinic.addBagNotA(graph, nodesForNotA.get(3), greenDeers); dinic.addBagNotA(graph, nodesForNotA.get(3), redDeers);
		dinic.addBagNotA(graph, nodesForNotA.get(4), greenTrains);
		dinic.addBagNotA(graph, nodesForNotA.get(5), greenDeers);
		dinic.addBagNotA(graph, nodesForNotA.get(6), redTrains);
		dinic.addBagNotA(graph, nodesForNotA.get(7), redDeers);

		dinic.transportToSink(graph, totalNodes + 1, redTrains); dinic.transportToSink(graph, totalNodes + 1, redDeers);
		dinic.transportToSink(graph, totalNodes + 1, greenTrains); dinic.transportToSink(graph, totalNodes + 1, greenDeers);
		String outputFileName = args[1];
		FileWriter outputWriting = new FileWriter(outputFileName);
		outputWriting.write((totalGifts - dinic.maxFlow(graph, 0, totalNodes + 1)));
		outputWriting.close();
		read.close();
		
	}
}
