module Plugin

import lang::java::Java8;
import lang::recaf::dat::Recaffeinate;
import ParseTree;

import IO;
import Message;
import util::IDE;

private str LANG_NAME = "Java Recaffeinated Data";

// relative to HOME dir
private str RECAF_RUNTIME_HOME = "/Users/pablo/git/recaf-data";

void main() {
  registerLanguage(LANG_NAME, "java", start[CompilationUnit](str src, loc org) {
    return parse(#start[CompilationUnit], src, org);
  });
  
  registerContributions(LANG_NAME, {
    builder(set[Message] (Tree tree) {
      if (start[CompilationUnit] cu := tree) {
        loc l = cu@\loc.top;
        l.extension = "java";
        newLoc = |project://recaf-data/test-generated/generated/<l.file>|;
        newCU = recaffeinate(cu);
        writeFile(newLoc, newCU);
        return newCU;
      }
      return {error("Not a <LANG_NAME> program", tree@\loc)};
    })
  });
}