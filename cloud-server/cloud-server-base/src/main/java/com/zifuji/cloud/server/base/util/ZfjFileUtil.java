package com.zifuji.cloud.server.base.util;


import cn.hutool.core.io.IoUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class ZfjFileUtil {
    private static Map<String, String> mapMimeType = new HashMap<String, String>();
    static {

        mapMimeType.put("123", "application/vnd.lotus-1-2-3");
        mapMimeType.put("3gp", "video/3gpp");
        mapMimeType.put("aab", "application/x-authoware-bin");
        mapMimeType.put("aam", "application/x-authoware-map");
        mapMimeType.put("aas", "application/x-authoware-seg");
        mapMimeType.put("ai", "application/postscript");
        mapMimeType.put("aif", "audio/x-aiff");
        mapMimeType.put("aifc", "audio/x-aiff");
        mapMimeType.put("aiff", "audio/x-aiff");
        mapMimeType.put("als", "audio/X-Alpha5");
        mapMimeType.put("amc", "application/x-mpeg");
        mapMimeType.put("ani", "application/octet-stream");
        mapMimeType.put("asc", "text/plain");
        mapMimeType.put("asd", "application/astound");
        mapMimeType.put("asf", "video/x-ms-asf");
        mapMimeType.put("asn", "application/astound");
        mapMimeType.put("asp", "application/x-asap");
        mapMimeType.put("asx", "video/x-ms-asf");
        mapMimeType.put("au", "audio/basic");
        mapMimeType.put("avb", "application/octet-stream");
        mapMimeType.put("avi", "video/x-msvideo");
        mapMimeType.put("awb", "audio/amr-wb");
        mapMimeType.put("bcpio", "application/x-bcpio");
        mapMimeType.put("bin", "application/octet-stream");
        mapMimeType.put("bld", "application/bld");
        mapMimeType.put("bld2", "application/bld2");
        mapMimeType.put("bmp", "application/x-MS-bmp");
        mapMimeType.put("bpk", "application/octet-stream");
        mapMimeType.put("bz2", "application/x-bzip2");
        mapMimeType.put("cal", "image/x-cals");
        mapMimeType.put("ccn", "application/x-cnc");
        mapMimeType.put("cco", "application/x-cocoa");
        mapMimeType.put("cdf", "application/x-netcdf");
        mapMimeType.put("cgi", "magnus-internal/cgi");
        mapMimeType.put("chat", "application/x-chat");
        mapMimeType.put("class", "application/octet-stream");
        mapMimeType.put("clp", "application/x-msclip");
        mapMimeType.put("cmx", "application/x-cmx");
        mapMimeType.put("co", "application/x-cult3d-object");
        mapMimeType.put("cod", "image/cis-cod");
        mapMimeType.put("cpio", "application/x-cpio");
        mapMimeType.put("cpt", "application/mac-compactpro");
        mapMimeType.put("crd", "application/x-mscardfile");
        mapMimeType.put("csh", "application/x-csh");
        mapMimeType.put("csm", "chemical/x-csml");
        mapMimeType.put("csml", "chemical/x-csml");
        mapMimeType.put("css", "text/css");
        mapMimeType.put("cur", "application/octet-stream");
        mapMimeType.put("dcm", "x-lml/x-evm");
        mapMimeType.put("dcr", "application/x-director");
        mapMimeType.put("dcx", "image/x-dcx");
        mapMimeType.put("dhtml", "text/html");
        mapMimeType.put("dir", "application/x-director");
        mapMimeType.put("dll", "application/octet-stream");
        mapMimeType.put("dmg", "application/octet-stream");
        mapMimeType.put("dms", "application/octet-stream");
        mapMimeType.put("doc", "application/msword");
        mapMimeType.put("docx", "application/msword");
        mapMimeType.put("dot", "application/x-dot");
        mapMimeType.put("dvi", "application/x-dvi");
        mapMimeType.put("dwf", "drawing/x-dwf");
        mapMimeType.put("dwg", "application/x-autocad");
        mapMimeType.put("dxf", "application/x-autocad");
        mapMimeType.put("dxr", "application/x-director");
        mapMimeType.put("ebk", "application/x-expandedbook");
        mapMimeType.put("emb", "chemical/x-embl-dl-nucleotide");
        mapMimeType.put("embl", "chemical/x-embl-dl-nucleotide");
        mapMimeType.put("eps", "application/postscript");
        mapMimeType.put("eri", "image/x-eri");
        mapMimeType.put("es", "audio/echospeech");
        mapMimeType.put("esl", "audio/echospeech");
        mapMimeType.put("etc", "application/x-earthtime");
        mapMimeType.put("etx", "text/x-setext");
        mapMimeType.put("evm", "x-lml/x-evm");
        mapMimeType.put("evy", "application/x-envoy");
        mapMimeType.put("exe", "application/octet-stream");
        mapMimeType.put("fh4", "image/x-freehand");
        mapMimeType.put("fh5", "image/x-freehand");
        mapMimeType.put("fhc", "image/x-freehand");
        mapMimeType.put("fif", "image/fif");
        mapMimeType.put("fm", "application/x-maker");
        mapMimeType.put("fpx", "image/x-fpx");
        mapMimeType.put("fvi", "video/isivideo");
        mapMimeType.put("gau", "chemical/x-gaussian-input");
        mapMimeType.put("gca", "application/x-gca-compressed");
        mapMimeType.put("gdb", "x-lml/x-gdb");
        mapMimeType.put("gif", "image/gif");
        mapMimeType.put("gps", "application/x-gps");
        mapMimeType.put("gtar", "application/x-gtar");
        mapMimeType.put("gz", "application/x-gzip");
        mapMimeType.put("hdf", "application/x-hdf");
        mapMimeType.put("hdm", "text/x-hdml");
        mapMimeType.put("hdml", "text/x-hdml");
        mapMimeType.put("hlp", "application/winhlp");
        mapMimeType.put("hqx", "application/mac-binhex40");
        mapMimeType.put("htm", "text/html");
        mapMimeType.put("html", "text/html");
        mapMimeType.put("hts", "text/html");
        mapMimeType.put("ice", "x-conference/x-cooltalk");
        mapMimeType.put("ico", "application/octet-stream");
        mapMimeType.put("ief", "image/ief");
        mapMimeType.put("ifm", "image/gif");
        mapMimeType.put("ifs", "image/ifs");
        mapMimeType.put("imy", "audio/melody");
        mapMimeType.put("ins", "application/x-NET-Install");
        mapMimeType.put("ips", "application/x-ipscript");
        mapMimeType.put("ipx", "application/x-ipix");
        mapMimeType.put("it", "audio/x-mod");
        mapMimeType.put("itz", "audio/x-mod");
        mapMimeType.put("ivr", "i-world/i-vrml");
        mapMimeType.put("j2k", "image/j2k");
        mapMimeType.put("jad", "text/vnd.sun.j2me.app-descriptor");
        mapMimeType.put("jam", "application/x-jam");
        mapMimeType.put("jar", "application/java-archive");
        mapMimeType.put("jnlp", "application/x-java-jnlp-file");
        mapMimeType.put("jpe", "image/jpeg");
        mapMimeType.put("jpeg", "image/jpeg");
        mapMimeType.put("jpg", "image/jpeg");
        mapMimeType.put("jpz", "image/jpeg");
        mapMimeType.put("js", "application/x-javascript");
        mapMimeType.put("jwc", "application/jwc");
        mapMimeType.put("kjx", "application/x-kjx");
        mapMimeType.put("lak", "x-lml/x-lak");
        mapMimeType.put("latex", "application/x-latex");
        mapMimeType.put("lcc", "application/fastman");
        mapMimeType.put("lcl", "application/x-digitalloca");
        mapMimeType.put("lcr", "application/x-digitalloca");
        mapMimeType.put("lgh", "application/lgh");
        mapMimeType.put("lha", "application/octet-stream");
        mapMimeType.put("lml", "x-lml/x-lml");
        mapMimeType.put("lmlpack", "x-lml/x-lmlpack");
        mapMimeType.put("lsf", "video/x-ms-asf");
        mapMimeType.put("lsx", "video/x-ms-asf");
        mapMimeType.put("lzh", "application/x-lzh");
        mapMimeType.put("m13", "application/x-msmediaview");
        mapMimeType.put("m14", "application/x-msmediaview");
        mapMimeType.put("m15", "audio/x-mod");
        mapMimeType.put("m3u", "audio/x-mpegurl");
        mapMimeType.put("m3url", "audio/x-mpegurl");
        mapMimeType.put("ma1", "audio/ma1");
        mapMimeType.put("ma2", "audio/ma2");
        mapMimeType.put("ma3", "audio/ma3");
        mapMimeType.put("ma5", "audio/ma5");
        mapMimeType.put("man", "application/x-troff-man");
        mapMimeType.put("map", "magnus-internal/imagemap");
        mapMimeType.put("mbd", "application/mbedlet");
        mapMimeType.put("mct", "application/x-mascot");
        mapMimeType.put("mdb", "application/x-msaccess");
        mapMimeType.put("mdz", "audio/x-mod");
        mapMimeType.put("me", "application/x-troff-me");
        mapMimeType.put("mel", "text/x-vmel");
        mapMimeType.put("mi", "application/x-mif");
        mapMimeType.put("mid", "audio/midi");
        mapMimeType.put("midi", "audio/midi");
        mapMimeType.put("mif", "application/x-mif");
        mapMimeType.put("mil", "image/x-cals");
        mapMimeType.put("mio", "audio/x-mio");
        mapMimeType.put("mmf", "application/x-skt-lbs");
        mapMimeType.put("mng", "video/x-mng");
        mapMimeType.put("mny", "application/x-msmoney");
        mapMimeType.put("moc", "application/x-mocha");
        mapMimeType.put("mocha", "application/x-mocha");
        mapMimeType.put("mod", "audio/x-mod");
        mapMimeType.put("mof", "application/x-yumekara");
        mapMimeType.put("mol", "chemical/x-mdl-molfile");
        mapMimeType.put("mop", "chemical/x-mopac-input");
        mapMimeType.put("mov", "video/quicktime");
        mapMimeType.put("movie", "video/x-sgi-movie");
        mapMimeType.put("mp2", "audio/x-mpeg");
        mapMimeType.put("mp3", "audio/x-mpeg");
        mapMimeType.put("mp4", "video/mp4");
        mapMimeType.put("mpc", "application/vnd.mpohun.certificate");
        mapMimeType.put("mpe", "video/mpeg");
        mapMimeType.put("mpeg", "video/mpeg");
        mapMimeType.put("mpg", "video/mpeg");
        mapMimeType.put("mpg4", "video/mp4");
        mapMimeType.put("mpga", "audio/mpeg");
        mapMimeType.put("mpn", "application/vnd.mophun.application");
        mapMimeType.put("mpp", "application/vnd.ms-project");
        mapMimeType.put("mps", "application/x-mapserver");
        mapMimeType.put("mrl", "text/x-mrml");
        mapMimeType.put("mrm", "application/x-mrm");
        mapMimeType.put("ms", "application/x-troff-ms");
        mapMimeType.put("mts", "application/metastream");
        mapMimeType.put("mtx", "application/metastream");
        mapMimeType.put("mtz", "application/metastream");
        mapMimeType.put("mzv", "application/metastream");
        mapMimeType.put("nar", "application/zip");
        mapMimeType.put("nbmp", "image/nbmp");
        mapMimeType.put("nc", "application/x-netcdf");
        mapMimeType.put("ndb", "x-lml/x-ndb");
        mapMimeType.put("ndwn", "application/ndwn");
        mapMimeType.put("nif", "application/x-nif");
        mapMimeType.put("nmz", "application/x-scream");
        mapMimeType.put("nokia-op-logo", "image/vnd.nok-oplogo-color");
        mapMimeType.put("npx", "application/x-netfpx");
        mapMimeType.put("nsnd", "audio/nsnd");
        mapMimeType.put("nva", "application/x-neva1");
        mapMimeType.put("oda", "application/oda");
        mapMimeType.put("oom", "application/x-AtlasMate-Plugin");
        mapMimeType.put("pac", "audio/x-pac");
        mapMimeType.put("pae", "audio/x-epac");
        mapMimeType.put("pan", "application/x-pan");
        mapMimeType.put("pbm", "image/x-portable-bitmap");
        mapMimeType.put("pcx", "image/x-pcx");
        mapMimeType.put("pda", "image/x-pda");
        mapMimeType.put("pdb", "chemical/x-pdb");
        mapMimeType.put("pdf", "application/pdf");
        mapMimeType.put("pfr", "application/font-tdpfr");
        mapMimeType.put("pgm", "image/x-portable-graymap");
        mapMimeType.put("pict", "image/x-pict");
        mapMimeType.put("pm", "application/x-perl");
        mapMimeType.put("pmd", "application/x-pmd");
        mapMimeType.put("png", "image/png");
        mapMimeType.put("pnm", "image/x-portable-anymap");
        mapMimeType.put("pnz", "image/png");
        mapMimeType.put("pot", "application/vnd.ms-powerpoint");
        mapMimeType.put("ppm", "image/x-portable-pixmap");
        mapMimeType.put("pps", "application/vnd.ms-powerpoint");
        mapMimeType.put("ppt", "application/vnd.ms-powerpoint");
        mapMimeType.put("pptx", "application/vnd.ms-powerpoint");
        mapMimeType.put("pqf", "application/x-cprplayer");
        mapMimeType.put("pqi", "application/cprplayer");
        mapMimeType.put("prc", "application/x-prc");
        mapMimeType.put("proxy", "application/x-ns-proxy-autoconfig");
        mapMimeType.put("ps", "application/postscript");
        mapMimeType.put("ptlk", "application/listenup");
        mapMimeType.put("pub", "application/x-mspublisher");
        mapMimeType.put("pvx", "video/x-pv-pvx");
        mapMimeType.put("qcp", "audio/vnd.qcelp");
        mapMimeType.put("qt", "video/quicktime");
        mapMimeType.put("qti", "image/x-quicktime");
        mapMimeType.put("qtif", "image/x-quicktime");
        mapMimeType.put("r3t", "text/vnd.rn-realtext3d");
        mapMimeType.put("ra", "audio/x-pn-realaudio");
        mapMimeType.put("ram", "audio/x-pn-realaudio");
        mapMimeType.put("rar", "application/x-rar-compressed");
        mapMimeType.put("ras", "image/x-cmu-raster");
        mapMimeType.put("rdf", "application/rdf+xml");
        mapMimeType.put("rf", "image/vnd.rn-realflash");
        mapMimeType.put("rgb", "image/x-rgb");
        mapMimeType.put("rlf", "application/x-richlink");
        mapMimeType.put("rm", "audio/x-pn-realaudio");
        mapMimeType.put("rmf", "audio/x-rmf");
        mapMimeType.put("rmm", "audio/x-pn-realaudio");
        mapMimeType.put("rmvb", "audio/x-pn-realaudio");
        mapMimeType.put("rnx", "application/vnd.rn-realplayer");
        mapMimeType.put("roff", "application/x-troff");
        mapMimeType.put("rp", "image/vnd.rn-realpix");
        mapMimeType.put("rpm", "audio/x-pn-realaudio-plugin");
        mapMimeType.put("rt", "text/vnd.rn-realtext");
        mapMimeType.put("rte", "x-lml/x-gps");
        mapMimeType.put("rtf", "application/rtf");
        mapMimeType.put("rtg", "application/metastream");
        mapMimeType.put("rtx", "text/richtext");
        mapMimeType.put("rv", "video/vnd.rn-realvideo");
        mapMimeType.put("rwc", "application/x-rogerwilco");
        mapMimeType.put("s3m", "audio/x-mod");
        mapMimeType.put("s3z", "audio/x-mod");
        mapMimeType.put("sca", "application/x-supercard");
        mapMimeType.put("scd", "application/x-msschedule");
        mapMimeType.put("sdf", "application/e-score");
        mapMimeType.put("sea", "application/x-stuffit");
        mapMimeType.put("sgm", "text/x-sgml");
        mapMimeType.put("sgml", "text/x-sgml");
        mapMimeType.put("sh", "application/x-sh");
        mapMimeType.put("shar", "application/x-shar");
        mapMimeType.put("shtml", "magnus-internal/parsed-html");
        mapMimeType.put("shw", "application/presentations");
        mapMimeType.put("si6", "image/si6");
        mapMimeType.put("si7", "image/vnd.stiwap.sis");
        mapMimeType.put("si9", "image/vnd.lgtwap.sis");
        mapMimeType.put("sis", "application/vnd.symbian.install");
        mapMimeType.put("sit", "application/x-stuffit");
        mapMimeType.put("skd", "application/x-Koan");
        mapMimeType.put("skm", "application/x-Koan");
        mapMimeType.put("skp", "application/x-Koan");
        mapMimeType.put("skt", "application/x-Koan");
        mapMimeType.put("slc", "application/x-salsa");
        mapMimeType.put("smd", "audio/x-smd");
        mapMimeType.put("smi", "application/smil");
        mapMimeType.put("smil", "application/smil");
        mapMimeType.put("smp", "application/studiom");
        mapMimeType.put("smz", "audio/x-smd");
        mapMimeType.put("snd", "audio/basic");
        mapMimeType.put("spc", "text/x-speech");
        mapMimeType.put("spl", "application/futuresplash");
        mapMimeType.put("spr", "application/x-sprite");
        mapMimeType.put("sprite", "application/x-sprite");
        mapMimeType.put("spt", "application/x-spt");
        mapMimeType.put("src", "application/x-wais-source");
        mapMimeType.put("stk", "application/hyperstudio");
        mapMimeType.put("stm", "audio/x-mod");
        mapMimeType.put("sv4cpio", "application/x-sv4cpio");
        mapMimeType.put("sv4crc", "application/x-sv4crc");
        mapMimeType.put("svf", "image/vnd");
        mapMimeType.put("svg", "image/svg-xml");
        mapMimeType.put("svh", "image/svh");
        mapMimeType.put("svr", "x-world/x-svr");
        mapMimeType.put("swf", "application/x-shockwave-flash");
        mapMimeType.put("swfl", "application/x-shockwave-flash");
        mapMimeType.put("t", "application/x-troff");
        mapMimeType.put("tad", "application/octet-stream");
        mapMimeType.put("talk", "text/x-speech");
        mapMimeType.put("tar", "application/x-tar");
        mapMimeType.put("taz", "application/x-tar");
        mapMimeType.put("tbp", "application/x-timbuktu");
        mapMimeType.put("tbt", "application/x-timbuktu");
        mapMimeType.put("tcl", "application/x-tcl");
        mapMimeType.put("tex", "application/x-tex");
        mapMimeType.put("texi", "application/x-texinfo");
        mapMimeType.put("texinfo", "application/x-texinfo");
        mapMimeType.put("tgz", "application/x-tar");
        mapMimeType.put("thm", "application/vnd.eri.thm");
        mapMimeType.put("tif", "image/tiff");
        mapMimeType.put("tiff", "image/tiff");
        mapMimeType.put("tki", "application/x-tkined");
        mapMimeType.put("tkined", "application/x-tkined");
        mapMimeType.put("toc", "application/toc");
        mapMimeType.put("toy", "image/toy");
        mapMimeType.put("tr", "application/x-troff");
        mapMimeType.put("trk", "x-lml/x-gps");
        mapMimeType.put("trm", "application/x-msterminal");
        mapMimeType.put("tsi", "audio/tsplayer");
        mapMimeType.put("tsp", "application/dsptype");
        mapMimeType.put("tsv", "text/tab-separated-values");
        mapMimeType.put("tsv", "text/tab-separated-values");
        mapMimeType.put("ttf", "application/octet-stream");
        mapMimeType.put("ttz", "application/t-time");
        mapMimeType.put("txt", "text/plain");
        mapMimeType.put("ult", "audio/x-mod");
        mapMimeType.put("ustar", "application/x-ustar");
        mapMimeType.put("uu", "application/x-uuencode");
        mapMimeType.put("uue", "application/x-uuencode");
        mapMimeType.put("vcd", "application/x-cdlink");
        mapMimeType.put("vcf", "text/x-vcard");
        mapMimeType.put("vdo", "video/vdo");
        mapMimeType.put("vib", "audio/vib");
        mapMimeType.put("viv", "video/vivo");
        mapMimeType.put("vivo", "video/vivo");
        mapMimeType.put("vmd", "application/vocaltec-media-desc");
        mapMimeType.put("vmf", "application/vocaltec-media-file");
        mapMimeType.put("vmi", "application/x-dreamcast-vms-info");
        mapMimeType.put("vms", "application/x-dreamcast-vms");
        mapMimeType.put("vox", "audio/voxware");
        mapMimeType.put("vqe", "audio/x-twinvq-plugin");
        mapMimeType.put("vqf", "audio/x-twinvq");
        mapMimeType.put("vql", "audio/x-twinvq");
        mapMimeType.put("vre", "x-world/x-vream");
        mapMimeType.put("vrml", "x-world/x-vrml");
        mapMimeType.put("vrt", "x-world/x-vrt");
        mapMimeType.put("vrw", "x-world/x-vream");
        mapMimeType.put("vts", "workbook/formulaone");
        mapMimeType.put("wav", "audio/x-wav");
        mapMimeType.put("wax", "audio/x-ms-wax");
        mapMimeType.put("wbmp", "image/vnd.wap.wbmp");
        mapMimeType.put("web", "application/vnd.xara");
        mapMimeType.put("wi", "image/wavelet");
        mapMimeType.put("wis", "application/x-InstallShield");
        mapMimeType.put("wm", "video/x-ms-wm");
        mapMimeType.put("wma", "audio/x-ms-wma");
        mapMimeType.put("wmd", "application/x-ms-wmd");
        mapMimeType.put("wmf", "application/x-msmetafile");
        mapMimeType.put("wml", "text/vnd.wap.wml");
        mapMimeType.put("wmlc", "application/vnd.wap.wmlc");
        mapMimeType.put("wmls", "text/vnd.wap.wmlscript");
        mapMimeType.put("wmlsc", "application/vnd.wap.wmlscriptc");
        mapMimeType.put("wmlscript", "text/vnd.wap.wmlscript");
        mapMimeType.put("wmv", "audio/x-ms-wmv");
        mapMimeType.put("wmx", "video/x-ms-wmx");
        mapMimeType.put("wmz", "application/x-ms-wmz");
        mapMimeType.put("wpng", "image/x-up-wpng");
        mapMimeType.put("wpt", "x-lml/x-gps");
        mapMimeType.put("wri", "application/x-mswrite");
        mapMimeType.put("wrl", "x-world/x-vrml");
        mapMimeType.put("wrz", "x-world/x-vrml");
        mapMimeType.put("ws", "text/vnd.wap.wmlscript");
        mapMimeType.put("wsc", "application/vnd.wap.wmlscriptc");
        mapMimeType.put("wv", "video/wavelet");
        mapMimeType.put("wvx", "video/x-ms-wvx");
        mapMimeType.put("wxl", "application/x-wxl");
        mapMimeType.put("x-gzip", "application/x-gzip");
        mapMimeType.put("xar", "application/vnd.xara");
        mapMimeType.put("xbm", "image/x-xbitmap");
        mapMimeType.put("xdm", "application/x-xdma");
        mapMimeType.put("xdma", "application/x-xdma");
        mapMimeType.put("xdw", "application/vnd.fujixerox.docuworks");
        mapMimeType.put("xht", "application/xhtml+xml");
        mapMimeType.put("xhtm", "application/xhtml+xml");
        mapMimeType.put("xhtml", "application/xhtml+xml");
        mapMimeType.put("xla", "application/vnd.ms-excel");
        mapMimeType.put("xlc", "application/vnd.ms-excel");
        mapMimeType.put("xll", "application/x-excel");
        mapMimeType.put("xlm", "application/vnd.ms-excel");
        mapMimeType.put("xls", "application/vnd.ms-excel");
        mapMimeType.put("xlsx", "application/vnd.ms-excel");
        mapMimeType.put("xlt", "application/vnd.ms-excel");
        mapMimeType.put("xlw", "application/vnd.ms-excel");
        mapMimeType.put("xm", "audio/x-mod");
        mapMimeType.put("xml", "text/xml");
        mapMimeType.put("xmz", "audio/x-mod");
        mapMimeType.put("xpi", "application/x-xpinstall");
        mapMimeType.put("xpm", "image/x-xpixmap");
        mapMimeType.put("xsit", "text/xml");
        mapMimeType.put("xsl", "text/xml");
        mapMimeType.put("xul", "text/xul");
        mapMimeType.put("xwd", "image/x-xwindowdump");
        mapMimeType.put("xyz", "chemical/x-pdb");
        mapMimeType.put("yz1", "application/x-yz1");
        mapMimeType.put("z", "application/x-compress");
        mapMimeType.put("zac", "application/x-zaurus-zac");
        mapMimeType.put("zip", "application/zip");

    }
    public static FileItem createFileItem(InputStream inputStream, String fileName) {
        FileItemFactory fileItemFactory = new DiskFileItemFactory(16, null);
        String textFileName = "file";
        FileItem fileItem = fileItemFactory.createItem(textFileName, MediaType.MULTIPART_FORM_DATA_VALUE, true, fileName);
        int byteRead = 0;
        byte[] buffer = new byte[1024];
        OutputStream os = null;
        try {
            os = fileItem.getOutputStream();
            while ((byteRead = inputStream.read(buffer)) != -1) {
                os.write(buffer, 0, byteRead);
            }

            inputStream.close();
        } catch (Exception e) {

        } finally {

        }
        return fileItem;
    }


    public static String getMimeType(String fileName){
        if (fileName == null || "".equals(fileName.trim()) || fileName.indexOf(".") <= 0){
            return "";
        }

        String suffix = fileName.substring(fileName.indexOf('.') + 1, fileName.length());
        String type = mapMimeType.get(suffix.toLowerCase());
        if (type == null || "".equals(type)) {
            return "";
        }

        return type;
    }


    public static String getUrlFileName(String urlFilePath) {
        String[] split = urlFilePath.split("\\/");
        return split[split.length - 1];
    }


    public static MultipartFile getMultipartFile(File file) {
        FileItem item = new DiskFileItemFactory().createItem("file"
                , MediaType.MULTIPART_FORM_DATA_VALUE
                , true
                , file.getName());
        try (InputStream input = new FileInputStream(file);
             OutputStream os = item.getOutputStream()) {
            // 流转移
            IoUtil.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        return new CommonsMultipartFile(item);
    }


}
