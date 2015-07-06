/*
 * Main script to setup algospref on installation
 */

//--utilizza le special variables provided by Gant
String source = "${pluginBasedir}"
String dest = "${basedir}"
source = dest + "/" + source + "/"
dest = dest + "/"

//--directory dell'applicazione
String appDir = "grails-app/"
String confDir = "${appDir}conf/"
String i18Dir = "${appDir}i18n/"

// copy readme into project
copyFile(source, dest, "${appDir}README", "README-Pref")

// create PrefBootStrap into project
newFile(source, dest, "${confDir}PrefBootStrap.groovy")

// copy i18n into project
copyFile(source, dest, "${i18Dir}algospref.properties")

public static newFile(String srcDirPath, String dstDirPath, String fileName) {
    newFile(srcDirPath, dstDirPath, fileName, fileName)
} // fine del metodo

public static copyFile(String srcDirPath, String dstDirPath, String fileName) {
    copyFile(srcDirPath, dstDirPath, fileName, fileName)
} // fine del metodo

public static moveFile(String srcDirPath, String dstDirPath, String fileName) {
    moveFile(srcDirPath, dstDirPath, fileName, fileName)
} // fine del metodo

public static newFile(String srcDirPath, String dstDirPath, String srcFileName, String dstFileName) {
    String srcFile = srcDirPath + srcFileName
    String destFile = dstDirPath + dstFileName

    newFile(srcFile, destFile)
    deleteFile(srcFile)
    print('------------')
    print('Creato (NON sovrascritto): ' + dstFileName)
    print('------------')
} // fine del metodo

public static copyFile(String srcDirPath, String dstDirPath, String srcFileName, String dstFileName) {
    String srcFile = srcDirPath + srcFileName
    String destFile = dstDirPath + dstFileName

    copyFile(srcFile, destFile)
    deleteFile(srcFile)
    print('------------')
    print('Creato (o sovrascritto) e poi cancellato: ' + dstFileName)
    print('------------')
} // fine del metodo

public static moveFile(String srcDirPath, String dstDirPath, String srcFileName, String dstFileName) {
    String srcFile = srcDirPath + srcFileName
    String destFile = dstDirPath + dstFileName

    copyFile(srcFile, destFile)
    print('------------')
    print('Creato (o sovrascritto): ' + dstFileName)
    print('------------')
} // fine del metodo

public static newFile(String srcFile, String destFile) {
    new AntBuilder().copy(file: srcFile, tofile: destFile, overwrite: false)
} // fine del metodo

public static copyFile(String srcFile, String destFile) {
    new AntBuilder().copy(file: srcFile, tofile: destFile, overwrite: true)
} // fine del metodo

public static deleteFile(String pathFile) {
    new AntBuilder().delete(file: pathFile)
} // fine del metodo
