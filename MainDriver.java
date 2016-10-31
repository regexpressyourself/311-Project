public class MainDriver {
	public static void main(String[] args) {
		String originalSource = "for (int a = 0; a == 3; a++) { statement; statement;}";
		String[] tokenArray;

		Parser source = new Parser(originalSource);

		tokenArray = source.getTokens();

		for (String token : tokenArray) {
			System.out.printf("Next token is %s\n", token);
		}

		/*

		   - Lexical Analysis -
		   LexicalAnalyzer le = new LexicalAnalyzer(tokenArray);
		   le.analyze();

		   - Compiler -
		   OurCompiler oc = new OurCompiler(tokenArray);
		   new.run();

		 */

	}

}
