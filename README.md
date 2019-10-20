QR Contacts [potential] Android application

Docs
-some stuff about documentation for the app


	Usage:
	-some stuff about how to use it

	Development:
		QRCodeManager:
			Methods and usage:
				encodeQRCode(String text, int width, int height, String filePath) throws WriterException, IOException
					text		- the String to be encoded
					width/height	- number of pixels the final image dimensions will use (try to be square)
					filePath	- formatted to route to dedicated image storage directory, most codes will be stored directly here and can be referenced by filename alone
				decodeQRCode(String filePath) throws IOException
					filePath	- formatted to route to dedicated image storage directory, most codes will be stored directly here and can be referenced by filename alone

