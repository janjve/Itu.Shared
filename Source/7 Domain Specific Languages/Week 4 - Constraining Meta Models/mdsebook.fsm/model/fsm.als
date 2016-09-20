one sig Model {
  machines : some FiniteStateMachine
}

sig FiniteStateMachine {
	states : set State,
	initial : one State
}

sig State {
	leavingTransitions: set Transition
}

sig Transition {
   target: one State
}

fact { Model.machines = FiniteStateMachine } 
fact { FiniteStateMachine.states = State } 
fact { State.leavingTransitions = Transition } 
fact { all s: State | one s.~states }
fact { all t: Transition | one t.~leavingTransitions }
fact { initial in states }

pred show {}
run show for 1 Model, 2 FiniteStateMachine, 2 State, 0 Transition
