package com.test.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class APCTest {
	private static final int IDEAL_SELLABLE_BLOCKS = 2;
	private static final int PROFIT_IDEAL_SELLABLE_BLOCKS = 3;
	private static final int LOSS_BLOCKS = -1;
	public static void main(String[] args) {
		Map<Integer, List<List<Integer>>> trunkList = getTreeTrunkList();
		calculateMaxProfit(trunkList);

	}

	private static void calculateMaxProfit(Map<Integer, List<List<Integer>>> trunkMap) {
		int i =1;
		for (List<List<Integer>> list : trunkMap.values()) {
			System.out.println("Case: " +i);
			System.out.println("Max Profit: " +list.stream()
					.map(a -> caluculateProfit(a))
					.reduce(0, (p1, p2) -> p1 + p2));
			i++;
		}
	}

	private static int caluculateProfit(List<Integer> a) {
		int profit = 0;
		for (Integer integer : a) {
			profit += calculateProfit(integer);
		}
		return profit;
	}

	private static int calculateProfit(Integer b) {
		int profit = 0;
		if (b == 1)
			profit += (b * LOSS_BLOCKS);
		else if (b >= IDEAL_SELLABLE_BLOCKS && b % IDEAL_SELLABLE_BLOCKS == 0) {
			profit += ((b / IDEAL_SELLABLE_BLOCKS) * PROFIT_IDEAL_SELLABLE_BLOCKS);
		} else {
			int p = b / IDEAL_SELLABLE_BLOCKS;
			int l = b % IDEAL_SELLABLE_BLOCKS;
			profit += ((p * PROFIT_IDEAL_SELLABLE_BLOCKS) + (l * LOSS_BLOCKS));
		}
		return profit;
	}

	private static Map<Integer, List<List<Integer>>> getTreeTrunkList() {
		Map<Integer, List<List<Integer>>> trunkMap = new HashMap<Integer, List<List<Integer>>>();
		try (Scanner sc = new Scanner(System.in)) {
			Integer key = -1;
			while (sc.hasNext()) {
				int noOfSawMills = Integer.parseInt(sc.next());
				if (noOfSawMills > 0) {
					List<List<Integer>> trunkList = new ArrayList<>();
					for (int i = 0; i < noOfSawMills; i++) {
						int notreeTrunk = sc.nextInt();
						int treeTrunks[] = new int[notreeTrunk];

						for (int j = 0; j < notreeTrunk; j++) {
							treeTrunks[j] = sc.nextInt();
						}
						trunkList.add(Arrays.stream(treeTrunks).boxed().collect(Collectors.toList()));
					}
					trunkMap.put(key++, trunkList);
				} else {
					break;
				}

			}

			return trunkMap;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trunkMap;
	}
}
