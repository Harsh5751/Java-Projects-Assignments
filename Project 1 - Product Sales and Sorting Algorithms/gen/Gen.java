package gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;
import java.util.UUID;

public class Gen {

	public static void main(String[] args) throws IOException {
		String studentId = "400132010";
		
		int seed = 0;
		for (int i = 0; i < studentId.length(); i++) {
			seed += ((int) studentId.charAt(i)) * 10 ^ i;
		}

		Writer wr = null;
		try {
			File f = new File("data\\a1_in.txt");
			wr = new BufferedWriter(new FileWriter(f));
			Random random = new Random(seed);

			for (int i = 0; i < 16; i++) {
				if (i > 0)
					wr.write(",");
				wr.write("{" + UUID.randomUUID() + ","
						+ random.nextInt(1024) + "}");
			}
			wr.write("\r\n");
			
			for (int i = 0; i < 64; i++) {
				if (i > 0)
					wr.write(",");
				wr.write("{" + UUID.randomUUID() + ","
						+ random.nextInt(4096) + "}");
			}
			wr.write("\r\n");
			
			for (int i = 0; i < 256; i++) {
				if (i > 0)
					wr.write(",");
				wr.write("{" + UUID.randomUUID() + ","
						+ random.nextInt(16384) + "}");
			}
			wr.write("\r\n");	
			
			for (int i = 0; i < 1024; i++) {
				if (i > 0)
					wr.write(",");
				wr.write("{" + UUID.randomUUID() + ","
						+ random.nextInt(65536) + "}");
			}
			wr.write("\r\n");

			for (int i = 0; i < 4096; i++) {
				if (i > 0)
					wr.write(",");
				wr.write("{" + UUID.randomUUID() + ","
						+ random.nextInt(1048576) + "}");
			}
			wr.write("\r\n");
		} 
		finally {
			if (wr != null)
				wr.close();
		}
	}

}
