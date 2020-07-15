/*
 *
 *
External sorting is required when the data being sorted do not fit into the main memory of a computing device (usually RAM) and instead they must reside in the slower external memory (usually a hard drive). External sorting typically uses a sort-merge strategy. In the sorting phase, chunks of data small enough to fit in main memory are read, sorted, and written out to a temporary file. In the merge phase, the sorted subfiles are combined into a single larger file.

For example, for sorting 900 megabytes of data using only 100 megabytes of RAM:
1) Read 100 MB of the data in main memory and sort by mergesort.
2) Write the sorted data to disk.
3) Repeat steps 1 and 2 until all of the data is in sorted 100 MB chunks (there are 900MB/100MB= 9 chunks), which now need to be merged into one single output file.
4) Read the first 10 MB (= 100MB/(9 chunks + 1))of each sorted chunk into input buffers in main memory and allocate the remaining 10 MB for an output buffer. (In practice, it might provide better performance to make the output buffer larger and the input buffers slightly smaller.)
5) Perform a 9-way merge and store the result in the output buffer. If the output buffer is full, write it to the final sorted file, and empty it. If any of the 9 input buffers gets empty, fill it with the next 10 MB of its associated 100 MB sorted chunk until no more data from the chunk is available.

NOTE: We are getting chunks from a Hardisk, permorming sorting on that chunk and storing that chunk back into the Hardisk. Again we are getting part from each chunk and merging them in main memory and storing into other hardisk.

Optimizing a little with additional passes -
1) For sorting, say, 50 GB in 100 MB of RAM, using a single merge pass isn't efficient: the disk seeks required to fill the input buffers with data from each of the 500 chunks (we read 100MB / 501 ~ 200KB from each chunk at one time) take up most of the sort time. Using two merge passes solves the problem. Then the sorting process might look like this:
2) Run the initial chunk-sorting pass as before.
3) Run a first merge pass combining 25 chunks at a time, resulting in 20 larger sorted chunks.
4) Run a second merge pass to merge the 20 larger sorted chunks.
 *
 *
 */

//Implementation -Sort a 4Gb file in 10Mb memory (or a system in which many processes run and total memory of that system is x Mb)
package do_later;
import java.util.*;
import java.io.*;

public class ExternalSort 
{
	/*We divide the file into small blocks. If the blocks are too small, we shall create too many temporary files. 
	If they are too big, we shall be using too much memory.*/ 
	public static long estimateBestSizeOfBlocks(File filetobesorted) 
	{
		long sizeoffile = filetobesorted.length();
		
		final int MAXTEMPFILES = 1024;//We don't want to open up much more than 1024 temporary files, better run out of memory first. (Even 1024 is stretching it.)
		
		long blocksize = sizeoffile / MAXTEMPFILES ;//On the other hand, we don't want to create many temporary filesfor naught. If blocksize is smaller than half the free memory, grow it.
		
		long freemem = Runtime.getRuntime().freeMemory();
		if( blocksize < freemem/2)
		    blocksize = freemem/2;
		else {
			if(blocksize >= freemem) 
			  System.err.println("We expect to run out of memory. ");
		}
		return blocksize;
	}

	/*This will simply load the file by blocks of x rows, then sort them in-memory, 
	and write the result to a bunch of temporary files that have to be merged later. 
	@param file some flat  file
	@return a list of temporary flat files*/
	public static List<File> sortInBatch(File file, Comparator<String> cmp) throws IOException 
	{
		List<File> files = new ArrayList<File>();
		BufferedReader fbr = new BufferedReader(new FileReader(file));
		long blocksize = estimateBestSizeOfBlocks(file);// in bytes
		try{
			List<String> tmplist =  new ArrayList<String>();
			String line = "";
			try {
				while(line != null) {
					long currentblocksize = 0; //in bytes
					while((currentblocksize < blocksize) &&((line = fbr.readLine()) != null) )//as long as you have 2MB
					{ 
						tmplist.add(line);
						currentblocksize += line.length(); //2+40; //java uses 16 bits per character + 40 bytes of overhead (estimated)
					}
					files.add(sortAndSave(tmplist,cmp));
					tmplist.clear();
				}
			}
			catch(EOFException oef) {
				if(tmplist.size()>0) {
					files.add(sortAndSave(tmplist,cmp));
					tmplist.clear();
				}
			}
		} 
		finally {
			fbr.close();
		}
		return files;
	}

	public static File sortAndSave(List<String> tmplist, Comparator<String> cmp) throws IOException  {
		Collections.sort(tmplist,cmp); 
		File newtmpfile = File.createTempFile("sortInBatch", "flatfile");
		newtmpfile.deleteOnExit();
		BufferedWriter fbw = new BufferedWriter(new FileWriter(newtmpfile));
		try {
			for(String r : tmplist) {
				fbw.write(r);
				fbw.newLine();
			}
		} 
		finally {
			fbw.close();
		}
		return newtmpfile;
	}

	/*This merges a bunch of temporary flat files 
	@param files
	@param output file
    @return The number of lines sorted. (P. Beaudoin)*/
	public static int mergeSortedFiles(List<File> files, File outputfile, final Comparator<String> cmp) throws IOException 
	{
		PriorityQueue<BinaryFileBuffer> pq = new PriorityQueue<BinaryFileBuffer>(11, new Comparator<BinaryFileBuffer>()
		{
			public int compare(BinaryFileBuffer i, BinaryFileBuffer j) {
                return cmp.compare(i.peek(), j.peek());
              }
            }
        );
		
		for (File f : files) {
			BinaryFileBuffer bfb = new BinaryFileBuffer(f);
			pq.add(bfb);
		}
		
		BufferedWriter fbw = new BufferedWriter(new FileWriter(outputfile));
		int rowcounter = 0;
		try {
			while(pq.size()>0) {
				BinaryFileBuffer bfb = pq.poll();
				String r = bfb.pop();
				fbw.write(r);
				fbw.newLine();
				++rowcounter;
				if(bfb.empty()) {
					bfb.fbr.close();
					bfb.originalfile.delete();// we don't need you anymore
				} 
				else {
					pq.add(bfb); // add it back
				}
			}
		} 
		finally { 
			fbw.close();
			for(BinaryFileBuffer bfb : pq ) bfb.close();
		}
		return rowcounter;
	}

	public static void main(String[] args) throws IOException {
		if(args.length<2) {
			System.out.println("please provide input and output file names");
			return;
		}
		String inputfile = args[0];
		String outputfile = args[1];
		Comparator<String> comparator = new Comparator<String>(){
			public int compare(String r1, String r2){
				return r1.compareTo(r2);
			}
		};
		List<File> l = sortInBatch(new File(inputfile), comparator);
		mergeSortedFiles(l, new File(outputfile), comparator);
	}
}

class BinaryFileBuffer  
{
	public static int BUFFERSIZE = 2048;
	public BufferedReader fbr;
	public File originalfile;
	private String cache;
	private boolean empty;
	
	public BinaryFileBuffer(File f) throws IOException {
		originalfile = f;
		fbr = new BufferedReader(new FileReader(f), BUFFERSIZE);
		reload();
	}
	
	public boolean empty() {
		return empty;
	}
	
	private void reload() throws IOException {
		try {
			if((this.cache = fbr.readLine()) == null){
				empty = true;
				cache = null;
			}
            else{
				empty = false;
			}
		} 
		catch(EOFException oef) {
			empty = true;
			cache = null;
		}
	}
	
	public void close() throws IOException {
		fbr.close();
	}
	
	public String peek() {
		if(empty()) 
			return null;
		return cache.toString();
	}
	
	public String pop() throws IOException {
		String answer = peek();
		reload();
		return answer;
	}
}