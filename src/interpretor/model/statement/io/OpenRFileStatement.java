package interpretor.model.statement.io;

import interpretor.collection.dictionary.IDictionary;
import interpretor.exceptions.model.statement.io.IOFileAlreadyExistsException;
import interpretor.exceptions.model.statement.io.IOFileNotFoundException;
import interpretor.model.ProgramState;
import interpretor.utils.FileDescriptorBuilder;
import interpretor.utils.FilePair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by nicu on 11/20/2016.
 */
public class OpenRFileStatement implements IOStatement {
    private String varFileId;
    private FilePair filePair;

    public OpenRFileStatement(String varFileId, String fileName) {
        this.varFileId = varFileId;
        this.filePair = new FilePair(fileName);
    }

    public String getVarFileId() {
        return varFileId;
    }

    public void setVarFileId(String varFileId) {
        this.varFileId = varFileId;
    }

    public FilePair getFilePair() {
        return filePair;
    }

    public void setFilePair(FilePair filePair) {
        this.filePair = filePair;
    }

    public ProgramState  execute(ProgramState programState) throws IOFileAlreadyExistsException, IOFileNotFoundException {
        IDictionary<Integer, FilePair> fileTable = programState.getFileTable();
        IDictionary<String, Integer> symbolTable = programState.getSymbolTable();
        BufferedReader bufferedReader;
        Integer fileDescriptor;

        if (fileTable.containsValue(filePair))
            throw new IOFileAlreadyExistsException();
        try {
            bufferedReader = new BufferedReader(new FileReader(filePair.getFile()));
            filePair.setBufferedReader(bufferedReader);
            fileDescriptor = FileDescriptorBuilder.getFileDescriptor();
            fileTable.put(fileDescriptor, filePair);
            symbolTable.put(varFileId, fileDescriptor);
            programState.setFileTable(fileTable);
            programState.setSymbolTable(symbolTable);
        }
        catch (FileNotFoundException e) {
            throw new IOFileNotFoundException();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpenRFileStatement that = (OpenRFileStatement) o;

        if (varFileId != null ? !varFileId.equals(that.varFileId) : that.varFileId != null) return false;
        return filePair != null ? filePair.equals(that.filePair) : that.filePair == null;

    }

    @Override
    public int hashCode() {
        int result = varFileId != null ? varFileId.hashCode() : 0;
        result = 31 * result + (filePair != null ? filePair.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "Open R File " + filePair + " having varFileId " + varFileId;
    }
}
