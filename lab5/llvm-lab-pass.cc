
#include "llvm/Analysis/CFGPrinter.h"
#include "llvm/IR/IRBuilder.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/LegacyPassManager.h"
#include "llvm/IR/Module.h"
#include "llvm/Support/FileSystem.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/Transforms/IPO/PassManagerBuilder.h"
#include "llvm/Pass.h"
#include "llvm/IR/Module.h"
#include "llvm/IR/Constants.h"
#include "llvm/IR/Type.h"
#include "llvm/IR/IRBuilder.h"


using namespace llvm;

namespace { 

class ExamplePass : public ModulePass {

 public:
  static char ID;
  ExamplePass() : ModulePass(ID) { }
  
  bool doInitialization(Module &M) override;
  bool runOnModule(Module &M) override;

};

}  // namespace

char ExamplePass::ID = 0;

bool ExamplePass::doInitialization(Module &M) {

  return true;

}

bool ExamplePass::runOnModule(Module &M) {
  
  errs() << "runOnModule\n";

  
  for (auto &F : M) {    
    if (F.getName() == "main"){
      // 1. call debug()
      BasicBlock &entryBlock = F.getEntryBlock();
      
      IntegerType *Int32Ty = IntegerType::getInt32Ty(M.getContext());
      Type *VoidTy = Type::getVoidTy(M.getContext());

      std::vector<Type *>FnArgs;
      FnArgs.push_back(Int32Ty);
      FunctionType *FnTy = FunctionType::get(VoidTy, FnArgs, false);
      FunctionCallee Fn = M.getOrInsertFunction("debug", FnTy);
       // insert IR
      BasicBlock::iterator IP = entryBlock.getFirstInsertionPt();
      IRBuilder<> IRB(&(*IP));
      IRB.CreateCall(Fn, ConstantInt::get(Int32Ty, 9527));
      

      // 2. change argv[0] to "aesophor is ghost !!!"
      // make a global string
      Value *Argv1 = IRB.CreateGlobalStringPtr("aesophor is ghost !!!");
      Argument* argv1 = F.getArg(1);



      // 3. change argc = 9487
      ConstantInt *newArgc = ConstantInt::get(Int32Ty, 9487);
      Argument* argc = F.getArg(0);
      argc->replaceAllUsesWith(newArgc);

    }
    /* add you code here */
    errs() << F.getName() << "\n";
    
  }
  // change argc

  // change argv


  return true;
  
}

static void registerExamplePass(const PassManagerBuilder &,
                                           legacy::PassManagerBase &PM) {

  PM.add(new ExamplePass());

}

static RegisterStandardPasses RegisterExamplePass(
    PassManagerBuilder::EP_OptimizerLast, registerExamplePass);

static RegisterStandardPasses RegisterExamplePass0(
    PassManagerBuilder::EP_EnabledOnOptLevel0, registerExamplePass);

