class longestSubstringWithReplacement{
	public int longestSubstringWithReplacement(String s, int k){
		int n = s.length();
		int maxLength = 0;

		for(int i=0;; i<n; i++) {
			int [] count = new int[26];
			int maxFreq = 0;

			for(int j=0;j<n;j++) {
				count[s.charAt(j) - 'A']++;
				maxFreq = Math.max(maxFreq, count[s.charAt(j)- 'A']);
				int windowSize = j - i +1;
				int totalNeed = windowSize - maxFreq;
				if(totalNeed <= k) {
					maxLength = Math.max(maxLength, windowSize);
				}
			}
		}
		return maxLength;
	}
	public void main(String[] args){
		String s1 = "AABABBA";
		int k1 = 1;
		System.out.println("Input: " + s1 + " " + k1);
		System.out.println("Output: " + longestSubstringWithReplacement(s1,k1));
	}
}
